package com.cfs.BMS.repository;

import com.cfs.BMS.entity.Show;
import com.cfs.BMS.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Long> {
    
  List<Show> findByMovieId(Long MovieId);
  List<Show> findByScreenId(Long screenId);
  List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);
  List<Show> findByScreenIdAndShowDate(Long screenId, LocalDate showDate);


}
