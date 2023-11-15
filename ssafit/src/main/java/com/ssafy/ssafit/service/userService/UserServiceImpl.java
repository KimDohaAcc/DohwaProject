package com.ssafy.ssafit.service.userService;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    // 카카오로그인시 db에 유저로 저장 메소드
    // userRepository
    private final UserRepository userRepository;

    @Override
    public User insertUser(User user) {
        if (userRepository.findById(user.getId()).isEmpty()) {
            if(!user.isIskakao() && user.getPassword()==null){
                throw new IllegalArgumentException("Password cannot be null");
            }
            else
              return userRepository.save(user);
        }

        return null;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
