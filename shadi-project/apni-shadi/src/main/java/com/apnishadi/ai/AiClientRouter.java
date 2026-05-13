package com.apnishadi.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AiClientRouter {
	private final String preferredProvider;
	private final OpenAiClient openAiClient;
	private final OllamaClient ollamaClient;
	private final GeminiClient geminiClient;

	public AiClientRouter(
			@Value("${app.ai.provider}") String preferredProvider,
			OpenAiClient openAiClient,
			OllamaClient ollamaClient,
			GeminiClient geminiClient
	) {
		this.preferredProvider = preferredProvider;
		this.openAiClient = openAiClient;
		this.ollamaClient = ollamaClient;
		this.geminiClient = geminiClient;
	}

	public AiTextClient preferredClient() {
		if ("openai".equalsIgnoreCase(preferredProvider)) {
			return openAiClient;
		}
		if ("ollama".equalsIgnoreCase(preferredProvider)) {
			return ollamaClient;
		}
		return geminiClient;
	}

	public AiTextClient secondaryClient() {
		if ("gemini".equalsIgnoreCase(preferredProvider)) {
			return openAiClient;
		}
		return ollamaClient;
	}

	public AiTextClient fallbackClient() {
		if ("ollama".equalsIgnoreCase(preferredProvider)) {
			return openAiClient;
		}
		return ollamaClient;
	}
}
