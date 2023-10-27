package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoNum;

    @Column(nullable = false, length =50, name = "video_title")
    private String videoTitle;

    @Column(nullable = false, length =300, name = "video_url")
    private String videoUrl;

    @Column(nullable = false, length =20, name = "video_sort")
    private String videoSort;
}
