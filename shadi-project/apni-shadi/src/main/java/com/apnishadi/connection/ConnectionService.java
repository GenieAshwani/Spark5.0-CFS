package com.apnishadi.connection;

import com.apnishadi.profile.Profile;
import com.apnishadi.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ConnectionService {
	private final ProfileRepository profileRepository;
	private final InterestRequestRepository interestRequestRepository;
	private final ConversationRepository conversationRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final ChatMessageBroadcaster chatMessageBroadcaster;

	public ConnectionService(
			ProfileRepository profileRepository,
			InterestRequestRepository interestRequestRepository,
			ConversationRepository conversationRepository,
			ChatMessageRepository chatMessageRepository,
			ChatMessageBroadcaster chatMessageBroadcaster
	) {
		this.profileRepository = profileRepository;
		this.interestRequestRepository = interestRequestRepository;
		this.conversationRepository = conversationRepository;
		this.chatMessageRepository = chatMessageRepository;
		this.chatMessageBroadcaster = chatMessageBroadcaster;
	}

	public List<InterestRequestResponse> getRequests(Long profileId, String box) {
		String normalizedBox = box == null || box.isBlank() ? "incoming" : box.trim().toLowerCase();
		return interestRequestRepository.findForProfile(profileId, normalizedBox).stream()
				.map(InterestRequestResponse::from)
				.toList();
	}

	@Transactional
	public InterestRequestResponse sendRequest(CreateInterestRequest command) {
		if (command.senderProfileId().equals(command.receiverProfileId())) {
			throw new ResponseStatusException(BAD_REQUEST, "You cannot send an interest request to yourself");
		}

		Profile sender = getProfile(command.senderProfileId());
		Profile receiver = getProfile(command.receiverProfileId());

		List<InterestRequest> existingRequests =
				interestRequestRepository.findBetweenProfiles(sender.getId(), receiver.getId());
		if (!existingRequests.isEmpty()) {
			return InterestRequestResponse.from(existingRequests.getFirst());
		}

		InterestRequest request = new InterestRequest();
		request.setSenderProfile(sender);
		request.setReceiverProfile(receiver);
		return InterestRequestResponse.from(interestRequestRepository.save(request));
	}

	@Transactional
	public ConversationResponse acceptRequest(Long requestId, Long receiverProfileId) {
		InterestRequest request = getRequest(requestId);
		if (!request.getReceiverProfile().getId().equals(receiverProfileId)) {
			throw new ResponseStatusException(FORBIDDEN, "Only the receiver can accept this request");
		}
		request.setStatus(InterestRequestStatus.ACCEPTED);
		request.setRespondedAt(OffsetDateTime.now());
		interestRequestRepository.save(request);

		Conversation conversation = conversationRepository.findByInterestRequestId(requestId)
				.orElseGet(() -> createConversation(request));
		return ConversationResponse.from(conversation);
	}

	@Transactional
	public InterestRequestResponse declineRequest(Long requestId, Long receiverProfileId) {
		InterestRequest request = getRequest(requestId);
		if (!request.getReceiverProfile().getId().equals(receiverProfileId)) {
			throw new ResponseStatusException(FORBIDDEN, "Only the receiver can decline this request");
		}
		request.setStatus(InterestRequestStatus.DECLINED);
		request.setRespondedAt(OffsetDateTime.now());
		return InterestRequestResponse.from(interestRequestRepository.save(request));
	}

	public List<ConversationResponse> getConversations(Long profileId) {
		return conversationRepository.findForProfile(profileId).stream()
				.map(ConversationResponse::from)
				.toList();
	}

	public List<ChatMessageResponse> getMessages(Long conversationId, Long profileId) {
		Conversation conversation = getConversation(conversationId);
		requireParticipant(conversation, profileId);
		return chatMessageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId).stream()
				.map(ChatMessageResponse::from)
				.toList();
	}

	@Transactional
	public ChatMessageResponse sendMessage(Long conversationId, SendChatMessageRequest command) {
		Conversation conversation = getConversation(conversationId);
		requireParticipant(conversation, command.senderProfileId());

		ChatMessage message = new ChatMessage();
		message.setConversation(conversation);
		message.setSenderProfile(getProfile(command.senderProfileId()));
		message.setMessage(command.message().trim());
		ChatMessageResponse response = ChatMessageResponse.from(chatMessageRepository.save(message));
		chatMessageBroadcaster.broadcast(response);
		return response;
	}

	private Conversation createConversation(InterestRequest request) {
		Conversation conversation = new Conversation();
		conversation.setInterestRequest(request);
		conversation.setProfileOne(request.getSenderProfile());
		conversation.setProfileTwo(request.getReceiverProfile());
		return conversationRepository.save(conversation);
	}

	private Profile getProfile(Long profileId) {
		return profileRepository.findById(profileId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Profile not found"));
	}

	private InterestRequest getRequest(Long requestId) {
		return interestRequestRepository.findById(requestId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Interest request not found"));
	}

	private Conversation getConversation(Long conversationId) {
		return conversationRepository.findById(conversationId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conversation not found"));
	}

	private static void requireParticipant(Conversation conversation, Long profileId) {
		if (!conversation.getProfileOne().getId().equals(profileId)
				&& !conversation.getProfileTwo().getId().equals(profileId)) {
			throw new ResponseStatusException(FORBIDDEN, "Profile is not part of this conversation");
		}
	}
}
