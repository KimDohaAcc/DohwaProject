package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "user_id")
    private Long id;
    @Column(nullable = false, length =40, name = "user_nickname")
    private String nickname;
    @Column(nullable = false, length =40, name = "user_account")
    private String account;
    @Column(nullable = true, length =40, name = "user_password")
    private String password;
    @Column(nullable = false, length =40, name = "user_iskakao")
    private boolean iskakao;

//    private Long generateRandomUserId() {
//        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
//    }
}
