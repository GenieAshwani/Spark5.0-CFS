package com.apnishadi.profile;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
	private final ProfileRepository profileRepository;

	public ProfileController(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@GetMapping
	public List<Profile> searchProfiles(
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String religion,
			@RequestParam(required = false) String gender
	) {
		return profileRepository.search(blankToNull(city), blankToNull(religion), blankToNull(gender));
	}

	@GetMapping("/{id}")
	public Profile getProfile(@PathVariable Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Profile not found"));
	}

	@PostMapping
	public Profile createProfile(@Valid @RequestBody Profile profile) {
		profile.setId(null);
		return profileRepository.save(profile);
	}

	private static String blankToNull(String value) {
		return value == null || value.isBlank() ? null : value.trim();
	}
}
