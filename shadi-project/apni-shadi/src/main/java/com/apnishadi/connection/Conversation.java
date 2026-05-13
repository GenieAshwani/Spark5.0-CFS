package com.apnishadi.connection;

import com.apnishadi.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.OffsetDateTime;

@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "interest_request_id")
	private InterestRequest interestRequest;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_one_id")
	private Profile profileOne;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_two_id")
	private Profile profileTwo;

	private OffsetDateTime createdAt = OffsetDateTime.now();

	public Long getId() {
		return id;
	}

	public InterestRequest getInterestRequest() {
		return interestRequest;
	}

	public void setInterestRequest(InterestRequest interestRequest) {
		this.interestRequest = interestRequest;
	}

	public Profile getProfileOne() {
		return profileOne;
	}

	public void setProfileOne(Profile profileOne) {
		this.profileOne = profileOne;
	}

	public Profile getProfileTwo() {
		return profileTwo;
	}

	public void setProfileTwo(Profile profileTwo) {
		this.profileTwo = profileTwo;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}
