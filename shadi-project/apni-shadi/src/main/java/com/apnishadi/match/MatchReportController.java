package com.apnishadi.match;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match-reports")
public class MatchReportController {
	private final MatchReportService matchReportService;

	public MatchReportController(MatchReportService matchReportService) {
		this.matchReportService = matchReportService;
	}

	@PostMapping
	public MatchReport createReport(@Valid @RequestBody MatchReportRequest request) {
		return matchReportService.createReport(request.profileOneId(), request.profileTwoId());
	}
}
