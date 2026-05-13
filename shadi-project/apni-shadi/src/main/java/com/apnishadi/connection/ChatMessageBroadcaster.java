package com.apnishadi.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatMessageBroadcaster {
	private final ObjectMapper objectMapper;
	private final ConcurrentHashMap<Long, Set<WebSocketSession>> sessionsByConversationId = new ConcurrentHashMap<>();

	public ChatMessageBroadcaster(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void register(Long conversationId, WebSocketSession session) {
		sessionsByConversationId
				.computeIfAbsent(conversationId, ignored -> ConcurrentHashMap.newKeySet())
				.add(session);
	}

	public void unregister(WebSocketSession session) {
		sessionsByConversationId.values().forEach(sessions -> sessions.remove(session));
	}

	public void broadcast(ChatMessageResponse message) {
		String json = toJson(message);
		TextMessage socketMessage = new TextMessage(json);
		Set<WebSocketSession> sessions = sessionsByConversationId.get(message.conversationId());
		if (sessions == null) {
			return;
		}
		sessions.removeIf(session -> !session.isOpen());
		sessions.forEach(session -> send(session, socketMessage));
	}

	private String toJson(ChatMessageResponse message) {
		try {
			return objectMapper.writeValueAsString(message);
		} catch (JsonProcessingException exception) {
			throw new IllegalStateException("Could not serialize chat message", exception);
		}
	}

	private static void send(WebSocketSession session, TextMessage message) {
		try {
			session.sendMessage(message);
		} catch (IOException exception) {
			try {
				session.close();
			} catch (IOException ignored) {
			}
		}
	}
}
