package com.apnishadi.connection;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;

@Component
public class ConversationWebSocketHandler extends TextWebSocketHandler {
	private final ChatMessageBroadcaster broadcaster;

	public ConversationWebSocketHandler(ChatMessageBroadcaster broadcaster) {
		this.broadcaster = broadcaster;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		broadcaster.register(conversationId(session.getUri()), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		broadcaster.unregister(session);
	}

	private static Long conversationId(URI uri) {
		if (uri == null) {
			throw new IllegalArgumentException("Conversation websocket URI is missing");
		}
		String path = uri.getPath();
		return Long.parseLong(path.substring(path.lastIndexOf('/') + 1));
	}
}
