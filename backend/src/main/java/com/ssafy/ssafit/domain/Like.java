package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`like`")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "like_num")
    private Long num;

    @ManyToOne
    @JoinColumn(nullable = false, name = "video_like")
    private Video video;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_like")
    private User user;
}
