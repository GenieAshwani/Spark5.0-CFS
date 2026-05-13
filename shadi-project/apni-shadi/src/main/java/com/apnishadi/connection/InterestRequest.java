package com.apnishadi.connection;

import com.apnishadi.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.OffsetDateTime;

@Entity
public class InterestRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_profile_id")
	private Profile senderProfile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_profile_id")
	private Profile receiverProfile;

	@Enumerated(EnumType.STRING)
	private InterestRequestStatus status = InterestRequestStatus.PENDING;

	private OffsetDateTime createdAt = OffsetDateTime.now();
	private OffsetDateTime respondedAt;

	@OneToOne(mappedBy = "interestRequest", fetch = FetchType.EAGER)
	private Conversation conversation;

	public Long getId() {
		return id;
	}

	public Profile getSenderProfile() {
		return senderProfile;
	}

	public void setSenderProfile(Profile senderProfile) {
		this.senderProfile = senderProfile;
	}

	public Profile getReceiverProfile() {
		return receiverProfile;
	}

	public void setReceiverProfile(Profile receiverProfile) {
		this.receiverProfile = receiverProfile;
	}

	public InterestRequestStatus getStatus() {
		return status;
	}

	public void setStatus(InterestRequestStatus status) {
		this.status = status;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public OffsetDateTime getRespondedAt() {
		return respondedAt;
	}

	public void setRespondedAt(OffsetDateTime respondedAt) {
		this.respondedAt = respondedAt;
	}

	public Conversation getConversation() {
		return conversation;
	}
}
