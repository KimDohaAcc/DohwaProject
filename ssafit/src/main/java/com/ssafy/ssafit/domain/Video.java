package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
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
