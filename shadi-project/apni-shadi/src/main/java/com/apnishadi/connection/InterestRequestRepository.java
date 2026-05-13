package com.apnishadi.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterestRequestRepository extends JpaRepository<InterestRequest, Long> {
	@Query("""
			select r from InterestRequest r
			where (:box = 'incoming' and r.receiverProfile.id = :profileId)
			   or (:box = 'outgoing' and r.senderProfile.id = :profileId)
			   or (:box = 'all' and (r.senderProfile.id = :profileId or r.receiverProfile.id = :profileId))
			order by r.createdAt desc
			""")
	List<InterestRequest> findForProfile(@Param("profileId") Long profileId, @Param("box") String box);

	@Query("""
			select r from InterestRequest r
			where ((r.senderProfile.id = :profileOneId and r.receiverProfile.id = :profileTwoId)
			    or (r.senderProfile.id = :profileTwoId and r.receiverProfile.id = :profileOneId))
			order by r.createdAt desc
			""")
	List<InterestRequest> findBetweenProfiles(@Param("profileOneId") Long profileOneId, @Param("profileTwoId") Long profileTwoId);

	Optional<InterestRequest> findFirstBySenderProfileIdAndReceiverProfileIdAndStatusOrderByCreatedAtDesc(
			Long senderProfileId,
			Long receiverProfileId,
			InterestRequestStatus status
	);
}
