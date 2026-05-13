package com.apnishadi.ai;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AiChatRequest(
		@NotNull Long profileOneId,
		@NotNull Long profileTwoId,
		@NotBlank String message
) {
}
