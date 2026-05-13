package com.apnishadi.ai;

public record AiChatResponse(
		String reply,
		boolean generatedByOpenAi,
		String model
) {
}
