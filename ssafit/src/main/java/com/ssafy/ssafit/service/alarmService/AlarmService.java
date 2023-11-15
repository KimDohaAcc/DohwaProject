package com.ssafy.ssafit.service.alarmService;

import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AlarmService {
    Alarm insertAlarm(Alarm alarm);




    @Transactional(readOnly = true)
    List<Alarm> findAlarmsByUser(User user);

    Alarm UpdateAlarm(Alarm alarm);

    void removeAlarm(List<Alarm>Alarm);
}
