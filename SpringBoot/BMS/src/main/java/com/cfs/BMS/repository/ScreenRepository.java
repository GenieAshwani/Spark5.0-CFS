package com.cfs.BMS.repository;

import com.cfs.BMS.entity.Screen;
import com.cfs.BMS.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen,Long> {

  List<Screen> findByTheaterId(Long theaterId);
}
