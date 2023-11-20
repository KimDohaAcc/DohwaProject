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
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "meal_num")
    private Long num;

    @Column(nullable = false, name = "meal_user")
    private String user;

    @Column(nullable = false, name = "meal_food")
    private String food;

    @Column(nullable = false, name = "meal_kcal")
    private String kcal;

    @Column(nullable = false, name = "meal_time")
    private LocalDateTime time;
}
