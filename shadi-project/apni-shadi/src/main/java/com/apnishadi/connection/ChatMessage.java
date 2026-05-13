package com.apnishadi.connection;

import com.apnishadi.profile.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.OffsetDateTime;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_profile_id")
	private Profile senderProfile;

	@Column(length = 2000)
	private String message;

	private OffsetDateTime createdAt = OffsetDateTime.now();

	public Long getId() {
		return id;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Profile getSenderProfile() {
		return senderProfile;
	}

	public void setSenderProfile(Profile senderProfile) {
		this.senderProfile = senderProfile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}
