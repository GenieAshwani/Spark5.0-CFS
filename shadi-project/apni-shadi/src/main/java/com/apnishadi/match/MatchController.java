package com.apnishadi.match;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
	private final MatchReportService matchReportService;

	public MatchController(MatchReportService matchReportService) {
		this.matchReportService = matchReportService;
	}

	@GetMapping
	public List<MatchCandidate> getMatches(@RequestParam Long profileId) {
		return matchReportService.getMatchesForProfile(profileId);
	}
}
