package com.ssafy.ssafit.service.alarmSettingService;

import com.ssafy.ssafit.domain.Alarm;
import com.ssafy.ssafit.domain.AlarmSetting;
import com.ssafy.ssafit.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AlarmSettingService {
    AlarmSetting insertAlarmSetting(AlarmSetting alarmSetting);

    @Transactional(readOnly = true)
    AlarmSetting findAlarmSettingByUser(User user);

    AlarmSetting UpdateAlarmSetting(AlarmSetting alarmSetting);

    void removeAlarmSetting(AlarmSetting alarmSetting);


}
