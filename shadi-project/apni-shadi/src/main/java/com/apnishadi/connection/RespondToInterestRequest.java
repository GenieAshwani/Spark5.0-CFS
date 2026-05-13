package com.apnishadi.connection;

import jakarta.validation.constraints.NotNull;

public record RespondToInterestRequest(@NotNull Long profileId) {
}
