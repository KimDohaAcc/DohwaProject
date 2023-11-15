package com.ssafy.ssafit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@Entity
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
    @Column(nullable = true, length =40, name = "user_password")
    private String password;
    @Column(nullable = false, length =40, name = "user_iskakao")
    private boolean iskakao;

//    private Long generateRandomUserId() {
//        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
//    }
}
