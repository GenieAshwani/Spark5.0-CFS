package com.apnishadi.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class GeminiClient implements AiTextClient {
	private final String apiKey;
	private final String model;
	private final String baseUrl;
	private final ObjectMapper objectMapper;
	private final HttpClient httpClient;

	public GeminiClient(
			@Value("${app.gemini.api-key}") String apiKey,
			@Value("${app.gemini.model}") String model,
			@Value("${app.gemini.base-url}") String baseUrl,
			ObjectMapper objectMapper
	) {
		this.apiKey = apiKey;
		this.model = model;
		this.baseUrl = baseUrl;
		this.objectMapper = objectMapper;
		this.httpClient = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(12))
				.build();
	}

	@Override
	public boolean isConfigured() {
		return apiKey != null && !apiKey.isBlank();
	}

	@Override
	public String model() {
		return model;
	}

	@Override
	public String provider() {
		return "gemini";
	}

	@Override
	public String createTextResponse(String instructions, String input) {
		if (!isConfigured()) {
			throw new IllegalStateException("GEMINI_API_KEY is not configured");
		}

		ObjectNode requestBody = createRequestBody(instructions, input);

		try {
			HttpResponse<String> response = sendWithRetry(requestBody.toString());
			if (response.statusCode() < 200 || response.statusCode() >= 300) {
				throw new IllegalStateException("Gemini request failed with status " + response.statusCode()
						+ ": " + responseBodySnippet(response.body()));
			}
			return extractText(response.body());
		} catch (IOException exception) {
			throw new IllegalStateException("Gemini request failed", exception);
		} catch (InterruptedException exception) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException("Gemini request was interrupted", exception);
		}
	}

	private ObjectNode createRequestBody(String instructions, String input) {
		ObjectNode requestBody = objectMapper.createObjectNode();
		ArrayNode contents = requestBody.putArray("contents");
		ObjectNode userContent = contents.addObject();
		userContent.put("role", "user");
		userContent.putArray("parts").addObject().put("text", instructions + "\n\nUser request and profile context:\n" + input);

		return requestBody;
	}

	private HttpResponse<String> sendWithRetry(String requestBody) throws IOException, InterruptedException {
		HttpResponse<String> response = null;
		for (int attempt = 1; attempt <= 3; attempt++) {
			response = httpClient.send(createRequest(requestBody), HttpResponse.BodyHandlers.ofString());
			if (!isRetryableStatus(response.statusCode())) {
				return response;
			}
			Thread.sleep(800L * attempt);
		}
		return response;
	}

	private HttpRequest createRequest(String requestBody) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/models/" + encode(model) + ":generateContent"))
				.timeout(Duration.ofSeconds(45))
				.header("x-goog-api-key", apiKey)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
				.build();
		return request;
	}

	private static boolean isRetryableStatus(int statusCode) {
		return statusCode == 429 || statusCode == 500 || statusCode == 502 || statusCode == 503 || statusCode == 504;
	}

	private static String responseBodySnippet(String responseBody) {
		if (responseBody == null || responseBody.isBlank()) {
			return "empty response body";
		}
		String compactBody = responseBody.replaceAll("\\s+", " ").trim();
		return compactBody.substring(0, Math.min(360, compactBody.length()));
	}

	private String extractText(String responseBody) throws IOException {
		JsonNode root = objectMapper.readTree(responseBody);
		StringBuilder text = new StringBuilder();
		for (JsonNode candidate : root.path("candidates")) {
			for (JsonNode part : candidate.path("content").path("parts")) {
				JsonNode textNode = part.path("text");
				if (textNode.isTextual()) {
					text.append(textNode.asText()).append("\n\n");
				}
			}
		}
		if (text.isEmpty()) {
			throw new IllegalStateException("Gemini response did not include text");
		}
		return text.toString().trim();
	}

	private static String encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}
}
