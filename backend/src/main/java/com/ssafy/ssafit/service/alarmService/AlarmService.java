package com.ssafy.ssafit.service.alarmService;

import com.ssafy.ssafit.domain.Alarm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AlarmService {
    Alarm insertAlarm(Alarm alarm);

    @Transactional(readOnly = true)
    List<Alarm> findAlarmsByUser(String user);

    void removeAlarm(String user);
}
