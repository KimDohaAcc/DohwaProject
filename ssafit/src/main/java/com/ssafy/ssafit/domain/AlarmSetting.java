package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSetting {
    @Id
    @Column(nullable = false, name = "alramsetting_user")
    private String user;

    @Column(name = "alramsetting_date")
    private String date;
}
