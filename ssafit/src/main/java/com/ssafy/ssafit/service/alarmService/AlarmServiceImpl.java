package com.ssafy.ssafit.service.alarmService;

import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Optional<Alarm> findByUser(User user) {
        return alarmRepository.findByUser(user);
    }

    @Override
    public Alarm UpdateAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);

    }

    @Override
    public void removeAlarm(Alarm alarm) {
        alarmRepository.delete(alarm);
    }
}
