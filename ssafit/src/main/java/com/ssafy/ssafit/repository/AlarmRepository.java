package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    void deleteAllByUser(String user);

    List<Alarm> findAlarmsByUser(String user);
}