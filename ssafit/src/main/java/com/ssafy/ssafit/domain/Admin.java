package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "admin_num")
    private Long num;

    @OneToOne
    @JoinColumn(name = "user_admin")
    private User user;

    @Column(nullable = false, length = 20, name = "admin_role")
    private String role;
}
