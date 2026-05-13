package com.apnishadi.auth;

import com.apnishadi.profile.Profile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
		@Email @NotBlank String email,
		@NotBlank String phone,
		@Size(min = 8) String password,
		@Valid Profile profile
) {
}
