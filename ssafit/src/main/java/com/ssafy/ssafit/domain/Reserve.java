package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
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
    private LocalDateTime time;

    @Column(nullable = false, name = "reserve_phone")
    private String phone;

    @Column(nullable = false, name = "reserve_store")
    private String store;
}