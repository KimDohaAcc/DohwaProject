package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "alram_num")
    private Long num;

    @Column(nullable = false, name = "alram_user")
    private String user;

    @Column(name = "alram_date")
    private LocalDateTime date;

    @Column(name="alarm_safe")
    private Boolean safe;
}
