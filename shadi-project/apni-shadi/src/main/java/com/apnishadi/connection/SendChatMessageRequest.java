package com.apnishadi.connection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendChatMessageRequest(
		@NotNull Long senderProfileId,
		@NotBlank String message
) {
}
