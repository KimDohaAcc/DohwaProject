package com.ssafy.ssafit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(nullable = false,name = "user_id")
    private Long id;
    @Column(nullable = false, length =40, name = "user_nickname")
    private String nickname;
    @Column(nullable = false, length =40, name = "user_account")
    private String account;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
