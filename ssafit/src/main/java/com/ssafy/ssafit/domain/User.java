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
    private String id;
    @Column(nullable = false, length =20)
    private String pw;
    @Column(nullable = false, length =20)
    private String name;
    private String address;
    private int phone;
    private String nickname;


}
