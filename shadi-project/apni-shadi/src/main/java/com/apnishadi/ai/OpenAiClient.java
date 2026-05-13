package com.apnishadi.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class OpenAiClient implements AiTextClient {
	private final String apiKey;
	private final String model;
	private final String baseUrl;
	private final ObjectMapper objectMapper;
	private final HttpClient httpClient;

	public OpenAiClient(
			@Value("${app.openai.api-key}") String apiKey,
			@Value("${app.openai.model}") String model,
			@Value("${app.openai.base-url}") String baseUrl,
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
		return "openai";
	}

	@Override
	public String createTextResponse(String instructions, String input) {
		if (!isConfigured()) {
			throw new IllegalStateException("OPENAI_API_KEY is not configured");
		}

		ObjectNode requestBody = objectMapper.createObjectNode();
		requestBody.put("model", model);
		requestBody.put("instructions", instructions);
		requestBody.put("input", input);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/responses"))
				.timeout(Duration.ofSeconds(45))
				.header("Authorization", "Bearer " + apiKey)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
				.build();

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() < 200 || response.statusCode() >= 300) {
				throw new IllegalStateException("OpenAI request failed with status " + response.statusCode());
			}
			return extractText(response.body());
		} catch (IOException exception) {
			throw new IllegalStateException("OpenAI request failed", exception);
		} catch (InterruptedException exception) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException("OpenAI request was interrupted", exception);
		}
	}

	private String extractText(String responseBody) throws IOException {
		JsonNode root = objectMapper.readTree(responseBody);
		JsonNode outputText = root.get("output_text");
		if (outputText != null && outputText.isTextual()) {
			return outputText.asText();
		}

		StringBuilder text = new StringBuilder();
		for (JsonNode outputItem : root.path("output")) {
			for (JsonNode contentItem : outputItem.path("content")) {
				JsonNode textNode = contentItem.path("text");
				if (textNode.isTextual()) {
					text.append(textNode.asText()).append("\n\n");
				}
			}
		}

		if (text.isEmpty()) {
			throw new IllegalStateException("OpenAI response did not include text");
		}
		return text.toString().trim();
	}
}
