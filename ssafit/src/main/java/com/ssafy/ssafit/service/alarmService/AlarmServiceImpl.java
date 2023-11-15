package com.ssafy.ssafit.service.alarmService;

import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmServiceImpl implements AlarmService {
    private final AlarmRepository alarmRepository;

    @Override
    public Alarm insertAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Alarm> findAlarmsByUser(User user) {
        return alarmRepository.findAlarmsByUser(user);
    }

    @Override
    public Alarm UpdateAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);

    }

    @Override
    public void removeAlarm(List<Alarm>userAlarms) {
        alarmRepository.delete((Alarm) userAlarms);
    }
}
