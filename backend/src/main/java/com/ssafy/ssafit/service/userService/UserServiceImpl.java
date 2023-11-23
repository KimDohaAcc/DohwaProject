package com.ssafy.ssafit.service.userService;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.UserRepository;
import com.ssafy.ssafit.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> extractUserFromToken(String sessionToken) {
        return Optional.ofNullable(jwtUtil.extractUserIdFromToken(sessionToken))
                .flatMap(this::findUserById);
    }
    @Override
    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}