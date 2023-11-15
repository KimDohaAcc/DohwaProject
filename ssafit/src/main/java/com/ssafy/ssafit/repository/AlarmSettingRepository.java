package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.AlarmSetting;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlarmSettingRepository extends JpaRepository<AlarmSetting, Long> {
    Optional<AlarmSetting> findByUser(User user);

    AlarmSetting findAlarmSettingByUser(User user);



}
