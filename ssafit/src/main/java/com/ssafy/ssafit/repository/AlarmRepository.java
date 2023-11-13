package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Optional<Alarm> findByUser(User user);
}
