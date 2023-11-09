package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "reserve_num")
    private Long num;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_reserve")
    private User user;


    @Column(nullable = false, name = "reserve_time")
    // 나중에 수정
    private String time;

    @Column(nullable = false, name = "reserve_phone")
    private int phone;
}
