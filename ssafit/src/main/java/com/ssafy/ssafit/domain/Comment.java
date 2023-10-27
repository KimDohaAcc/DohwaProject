package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "comment_num")
    private Long num;

    @Column(nullable = false, length = 1000, name = "comment_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "comment_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_board")
    private Board board;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt  = LocalDateTime.now();

    @Override
    public String toString() {
        return "Comment{" +
                "num=" + num +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", board=" + board +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
