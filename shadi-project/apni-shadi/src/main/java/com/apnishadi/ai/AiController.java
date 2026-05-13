package com.apnishadi.ai;

import com.apnishadi.match.MatchReport;
import com.apnishadi.match.MatchReportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {
	private final MatchReportService matchReportService;
	private final AiAdvisorService aiAdvisorService;

	public AiController(MatchReportService matchReportService, AiAdvisorService aiAdvisorService) {
		this.matchReportService = matchReportService;
		this.aiAdvisorService = aiAdvisorService;
	}

	@PostMapping("/chat")
	public AiChatResponse chat(@Valid @RequestBody AiChatRequest request) {
		MatchReport report = matchReportService.createCalculatedReport(request.profileOneId(), request.profileTwoId());
		return aiAdvisorService.chat(report.profileOne(), report.profileTwo(), request.message(), report);
	}
}
