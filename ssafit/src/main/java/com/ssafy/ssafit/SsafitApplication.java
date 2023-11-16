package com.ssafy.ssafit;

import com.ssafy.ssafit.domain.AlarmSetting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class SsafitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsafitApplication.class, args);
    }
}
