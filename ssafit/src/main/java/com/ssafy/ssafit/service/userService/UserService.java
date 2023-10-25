package com.ssafy.ssafit.service.userService;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.dto.UserDto;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface UserService {
        public void insertUser(UserDto userDto);

    public Optional<User> findUserById(Long id);
}
