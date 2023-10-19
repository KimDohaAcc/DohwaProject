package com.ssafy.ssafit.service.userService;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public void insertUser(User user){
        userRepository.save(user);
    }
    // CRUD
    /*
    insert
    update
    find
    delete
     */

}
