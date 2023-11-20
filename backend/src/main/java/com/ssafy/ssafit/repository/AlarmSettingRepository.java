package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.AlarmSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlarmSettingRepository extends JpaRepository<AlarmSetting, Long> {
    Optional<AlarmSetting> findByUser(String user);

    AlarmSetting findAlarmSettingByUser(String user);



}
