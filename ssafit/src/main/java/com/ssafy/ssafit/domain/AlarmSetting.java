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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "alramSetting_num")
    private Long num;

    @OneToOne
    @JoinColumn(name = "user_alramSetting_id")
    private User user;

    @Column(name = "alramSetting_date")
    private LocalDateTime date;



}
