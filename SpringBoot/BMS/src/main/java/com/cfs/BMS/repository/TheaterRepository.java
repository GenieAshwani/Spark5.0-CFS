package com.cfs.BMS.repository;

import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater,Long> {

  List<Theater> findByCityId(Long cityId);
}
