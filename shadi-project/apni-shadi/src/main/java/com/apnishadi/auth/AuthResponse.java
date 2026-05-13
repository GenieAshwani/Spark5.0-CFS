package com.apnishadi.auth;

import com.apnishadi.profile.Profile;

public record AuthResponse(
		String token,
		Profile profile
) {
}
