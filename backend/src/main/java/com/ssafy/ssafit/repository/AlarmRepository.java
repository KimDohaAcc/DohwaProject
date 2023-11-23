package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    void deleteAllByUser(String user);

    List<Alarm> findAlarmsByUser(String user);
}