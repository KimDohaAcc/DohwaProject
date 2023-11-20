package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Admin;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUser(User user);
}
