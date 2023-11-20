package com.ssafy.ssafit.service.alarmSettingService;

import com.ssafy.ssafit.domain.AlarmSetting;
import org.springframework.transaction.annotation.Transactional;

public interface AlarmSettingService {
    AlarmSetting insertAlarmSetting(AlarmSetting alarmSetting);

    @Transactional(readOnly = true)
    AlarmSetting findAlarmSettingByUser(String user);

    AlarmSetting updateAlarmSetting(AlarmSetting alarmSetting);

    void removeAlarmSetting(AlarmSetting alarmSetting);


}
