package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
