package com.cfs.BMS.repository;

import com.cfs.BMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

   Optional<User> findByEmail(String email);
   boolean existsByEmail(String email);
}
