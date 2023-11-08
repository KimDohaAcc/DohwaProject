package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "follow_num")
    private Long num;

    @ManyToOne
    @JoinColumn(name = "user_follower")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "user_followee")
    private User followee;
}
