package com.apnishadi.connection;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByConversationIdOrderByCreatedAtAsc(Long conversationId);
}
