package com.ssafy.ssafit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private int videoNum;
    @Column(nullable = false, length =50)
    private String videoTitle;
    @Column(nullable = false, length =300)
    private String videoUrl;
    @Column(nullable = false, length =20)
    private String videoSort;

}
