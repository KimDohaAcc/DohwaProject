package com.ssafy.ssafit.service.alarmService;

import com.ssafy.ssafit.domain.Admin;
import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.User;

import java.util.Optional;

public interface AlarmService {
    Alarm insertAlarm(Alarm alarm);

    Optional<Alarm> findByUser(User user);

    Alarm UpdateAlarm(Alarm alarm);

    void removeAlarm(Alarm alarm);
}
