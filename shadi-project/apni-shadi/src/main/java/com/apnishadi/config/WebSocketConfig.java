package com.apnishadi.config;

import com.apnishadi.connection.ConversationWebSocketHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private final ConversationWebSocketHandler conversationWebSocketHandler;
	private final String[] allowedOrigins;

	public WebSocketConfig(
			ConversationWebSocketHandler conversationWebSocketHandler,
			@Value("${app.cors.allowed-origins}") String allowedOrigins
	) {
		this.conversationWebSocketHandler = conversationWebSocketHandler;
		this.allowedOrigins = allowedOrigins.split(",");
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(conversationWebSocketHandler, "/ws/conversations/*")
				.setAllowedOrigins(allowedOrigins);
	}
}
