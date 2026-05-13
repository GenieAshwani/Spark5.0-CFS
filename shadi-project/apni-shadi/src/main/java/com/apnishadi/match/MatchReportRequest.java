package com.apnishadi.match;

import jakarta.validation.constraints.NotNull;

public record MatchReportRequest(
		@NotNull Long profileOneId,
		@NotNull Long profileTwoId
) {
}
