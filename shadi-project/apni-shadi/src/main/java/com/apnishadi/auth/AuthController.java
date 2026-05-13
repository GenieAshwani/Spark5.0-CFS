package com.apnishadi.auth;

import com.apnishadi.profile.Profile;
import com.apnishadi.profile.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AccountRepository accountRepository;
	private final ProfileRepository profileRepository;
	private final PasswordHasher passwordHasher;

	public AuthController(AccountRepository accountRepository, ProfileRepository profileRepository, PasswordHasher passwordHasher) {
		this.accountRepository = accountRepository;
		this.profileRepository = profileRepository;
		this.passwordHasher = passwordHasher;
	}

	@PostMapping("/signup")
	public AuthResponse signup(@Valid @RequestBody SignupRequest request) {
		if (accountRepository.existsByEmailIgnoreCase(request.email())) {
			throw new ResponseStatusException(BAD_REQUEST, "Email is already registered");
		}

		Profile profile = request.profile();
		profile.setId(null);
		Profile savedProfile = profileRepository.save(profile);

		Account account = new Account();
		account.setEmail(request.email().trim().toLowerCase());
		account.setPhone(request.phone().trim());
		account.setPasswordHash(passwordHasher.hash(request.password()));
		account.setProfile(savedProfile);
		accountRepository.save(account);

		return authResponse(savedProfile);
	}

	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody LoginRequest request) {
		Account account = accountRepository.findByEmailIgnoreCase(request.email())
				.orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED, "Invalid email or password"));
		if (!passwordHasher.matches(request.password(), account.getPasswordHash())) {
			throw new ResponseStatusException(UNAUTHORIZED, "Invalid email or password");
		}
		return authResponse(account.getProfile());
	}

	private static AuthResponse authResponse(Profile profile) {
		return new AuthResponse("mvp-profile-" + profile.getId(), profile);
	}
}
