package com.cfs.BMS.repository;

import com.cfs.BMS.entity.Seat;
import com.cfs.BMS.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

  List<Seat> findByScreenId(Long screenId);
}
