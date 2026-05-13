package com.apnishadi.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
public class OllamaClient implements AiTextClient {
	private final String baseUrl;
	private final String model;
	private final ObjectMapper objectMapper;
	private final HttpClient httpClient;

	public OllamaClient(
			@Value("${app.ollama.base-url}") String baseUrl,
			@Value("${app.ollama.model}") String model,
			ObjectMapper objectMapper
	) {
		this.baseUrl = baseUrl;
		this.model = model;
		this.objectMapper = objectMapper;
		this.httpClient = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(5))
				.build();
	}

	@Override
	public boolean isConfigured() {
		return isRunning();
	}

	@Override
	public String model() {
		return model;
	}

	@Override
	public String provider() {
		return "ollama";
	}

	@Override
	public String createTextResponse(String instructions, String input) {
		ObjectNode requestBody = objectMapper.createObjectNode();
		requestBody.put("model", model);
		requestBody.put("stream", false);

		ArrayNode messages = requestBody.putArray("messages");
		addMessage(messages, "system", instructions);
		addMessage(messages, "user", input);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/api/chat"))
				.timeout(Duration.ofSeconds(90))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
				.build();

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() < 200 || response.statusCode() >= 300) {
				throw new IllegalStateException("Ollama request failed with status " + response.statusCode());
			}
			JsonNode root = objectMapper.readTree(response.body());
			JsonNode content = root.path("message").path("content");
			if (!content.isTextual() || content.asText().isBlank()) {
				throw new IllegalStateException("Ollama response did not include message content");
			}
			return content.asText();
		} catch (IOException exception) {
			throw new IllegalStateException("Ollama request failed. Is Ollama running and is the model pulled?", exception);
		} catch (InterruptedException exception) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException("Ollama request was interrupted", exception);
		}
	}

	private boolean isRunning() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + "/api/tags"))
				.timeout(Duration.ofSeconds(2))
				.GET()
				.build();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return response.statusCode() >= 200 && response.statusCode() < 300;
		} catch (IOException exception) {
			return false;
		} catch (InterruptedException exception) {
			Thread.currentThread().interrupt();
			return false;
		}
	}

	private static void addMessage(ArrayNode messages, String role, String content) {
		ObjectNode message = messages.addObject();
		message.put("role", role);
		message.put("content", content);
	}
}
