package com.ssafy.ssafit.service.alarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleNotification(String timeString, String userTimeZone) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime userTime = LocalDateTime.parse(timeString, formatter);


            long initialDelay = calculateInitialDelay(userTime, userTimeZone);


            scheduler.scheduleAtFixedRate(() -> sendNotification(), initialDelay, 24, TimeUnit.HOURS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long calculateInitialDelay(LocalDateTime userTime, String userTimeZone) {

        ZonedDateTime now = ZonedDateTime.now();
        ZoneId userZoneId = ZoneId.of(userTimeZone);
        ZonedDateTime userDateTime = userTime.atZone(userZoneId);


        Duration duration = Duration.between(now, userDateTime);


        long initialDelay = Math.max(duration.toMillis(), 0);

        return initialDelay;
    }

    private void sendNotification() {

        System.out.println("알림이 발송되었습니다!");
    }
}
