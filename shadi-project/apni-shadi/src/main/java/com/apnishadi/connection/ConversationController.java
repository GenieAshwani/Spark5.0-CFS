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
@RequestMapping("/api/conversations")
public class ConversationController {
	private final ConnectionService connectionService;

	public ConversationController(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}

	@GetMapping
	public List<ConversationResponse> getConversations(@RequestParam Long profileId) {
		return connectionService.getConversations(profileId);
	}

	@GetMapping("/{conversationId}/messages")
	public List<ChatMessageResponse> getMessages(@PathVariable Long conversationId, @RequestParam Long profileId) {
		return connectionService.getMessages(conversationId, profileId);
	}

	@PostMapping("/{conversationId}/messages")
	public ChatMessageResponse sendMessage(
			@PathVariable Long conversationId,
			@Valid @RequestBody SendChatMessageRequest command
	) {
		return connectionService.sendMessage(conversationId, command);
	}
}
