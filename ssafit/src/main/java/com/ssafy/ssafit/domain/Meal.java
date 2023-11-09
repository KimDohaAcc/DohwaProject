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

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_meal")
    private User user;

    @Column(nullable = false, name = "meal_food")
    private String food;

    @Column(nullable = false, name = "meal_kcal")
    private String kcal;

    @Column(nullable = false, name = "meal_time")
    private LocalDateTime time;
}
