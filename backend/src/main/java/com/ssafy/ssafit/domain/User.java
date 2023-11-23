package com.ssafy.ssafit.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @Column(nullable = false, name = "user_id")
    private Long id;
    @Column(nullable = false, length = 40, name = "user_nickname")
    private String nickname;
    @Column(nullable = false, length = 40, name = "user_account")
    private String account;
    @Column(nullable = true, length = 40, name = "user_password")
    private String password;
    @Column(nullable = false, length = 40, name = "user_iskakao")
    private boolean iskakao;

    public User(Long id, String nickname, String account, String password, boolean iskakao) {
        if (id == null) {
            this.id = generateRandomUserId();
        } else {
            this.id = id;
        }
        this.nickname = nickname;
        this.account = account;
        this.password = password;
        this.iskakao = iskakao;
    }

    private Long generateRandomUserId() {
        return UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE;
    }
}