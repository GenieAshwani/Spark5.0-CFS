package com.apnishadi.connection;

import com.apnishadi.profile.Profile;

import java.time.OffsetDateTime;

public record InterestRequestResponse(
		Long id,
		Profile senderProfile,
		Profile receiverProfile,
		InterestRequestStatus status,
		Long conversationId,
		OffsetDateTime createdAt,
		OffsetDateTime respondedAt
) {
	static InterestRequestResponse from(InterestRequest request) {
		Long conversationId = request.getConversation() == null ? null : request.getConversation().getId();
		return new InterestRequestResponse(
				request.getId(),
				request.getSenderProfile(),
				request.getReceiverProfile(),
				request.getStatus(),
				conversationId,
				request.getCreatedAt(),
				request.getRespondedAt()
		);
	}
}
