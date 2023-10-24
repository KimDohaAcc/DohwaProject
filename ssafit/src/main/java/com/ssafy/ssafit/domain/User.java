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
public class User {
    @Id
    @Column(length=40)
    private String account_email;
    @Column(nullable = false, length =40)
    private String profile_nickname;
    @Column(nullable = false, length =40)
    private String user_nickname;
}
