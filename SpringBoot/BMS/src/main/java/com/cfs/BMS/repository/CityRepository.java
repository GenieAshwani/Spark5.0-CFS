package com.cfs.BMS.repository;

import com.cfs.BMS.entity.City;
import com.cfs.BMS.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {


}
