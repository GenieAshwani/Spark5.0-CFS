package com.apnishadi.connection;

import com.apnishadi.profile.Profile;

import java.time.OffsetDateTime;

public record ConversationResponse(
		Long id,
		Long interestRequestId,
		Profile profileOne,
		Profile profileTwo,
		OffsetDateTime createdAt
) {
	static ConversationResponse from(Conversation conversation) {
		return new ConversationResponse(
				conversation.getId(),
				conversation.getInterestRequest().getId(),
				conversation.getProfileOne(),
				conversation.getProfileTwo(),
				conversation.getCreatedAt()
		);
	}
}
