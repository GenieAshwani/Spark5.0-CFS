package com.apnishadi.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findByFullName(String fullName);

	@Query("""
			select p from Profile p
			where (:city is null or lower(p.city) = lower(:city))
			and (:religion is null or lower(p.religion) = lower(:religion))
			and (:gender is null or lower(p.gender) = lower(:gender))
			order by p.id
			""")
	List<Profile> search(
			@Param("city") String city,
			@Param("religion") String religion,
			@Param("gender") String gender
	);
}
