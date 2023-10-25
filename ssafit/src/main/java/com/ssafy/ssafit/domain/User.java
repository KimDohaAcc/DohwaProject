package com.ssafy.ssafit.domain;

import com.ssafy.ssafit.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
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

    public User(UserDto userDto){
        this.id = userDto.getId();
        this.nickname = userDto.getNickname();
        this.account = userDto.getAccount();
    }
}
