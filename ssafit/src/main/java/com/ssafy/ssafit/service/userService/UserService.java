package com.ssafy.ssafit.service.userService;

import com.ssafy.ssafit.domain.User;

import java.util.Optional;

public interface UserService {
    User insertUser(User user);
    Optional<User> findUserById(Long id);
}
