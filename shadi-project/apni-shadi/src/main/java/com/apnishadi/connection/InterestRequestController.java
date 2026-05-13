package com.apnishadi.connection;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/interest-requests")
public class InterestRequestController {
	private final ConnectionService connectionService;

	public InterestRequestController(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}

	@GetMapping
	public List<InterestRequestResponse> getRequests(
			@RequestParam Long profileId,
			@RequestParam(defaultValue = "incoming") String box
	) {
		return connectionService.getRequests(profileId, box);
	}

	@PostMapping
	public InterestRequestResponse sendRequest(@Valid @RequestBody CreateInterestRequest command) {
		return connectionService.sendRequest(command);
	}

	@PostMapping("/{requestId}/accept")
	public ConversationResponse acceptRequest(
			@PathVariable Long requestId,
			@Valid @RequestBody RespondToInterestRequest command
	) {
		return connectionService.acceptRequest(requestId, command.profileId());
	}

	@PostMapping("/{requestId}/decline")
	public InterestRequestResponse declineRequest(
			@PathVariable Long requestId,
			@Valid @RequestBody RespondToInterestRequest command
	) {
		return connectionService.declineRequest(requestId, command.profileId());
	}
}
