package com.apnishadi.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	Optional<Conversation> findByInterestRequestId(Long interestRequestId);

	@Query("""
			select c from Conversation c
			where c.profileOne.id = :profileId or c.profileTwo.id = :profileId
			order by c.createdAt desc
			""")
	List<Conversation> findForProfile(@Param("profileId") Long profileId);
}
