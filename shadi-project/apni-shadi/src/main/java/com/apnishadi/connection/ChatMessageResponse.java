package com.apnishadi.connection;

import java.time.OffsetDateTime;

public record ChatMessageResponse(
		Long id,
		Long conversationId,
		Long senderProfileId,
		String senderName,
		String message,
		OffsetDateTime createdAt
) {
	static ChatMessageResponse from(ChatMessage message) {
		return new ChatMessageResponse(
				message.getId(),
				message.getConversation().getId(),
				message.getSenderProfile().getId(),
				message.getSenderProfile().getFullName(),
				message.getMessage(),
				message.getCreatedAt()
		);
	}
}
