package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "alram_num")
    private Long num;

    @OneToOne
    @JoinColumn(name = "user_alram_id")
    private User user;

    @Column(name = "alram_date")
    private LocalDateTime date;


    @Column(name="alarm_safe")
    private Boolean safe;

}
