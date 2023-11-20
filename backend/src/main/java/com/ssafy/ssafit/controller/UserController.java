package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.userService.UserService;
import com.ssafy.ssafit.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/user")
    public ResponseEntity<User> regist(@RequestBody User user) {
        return Optional.ofNullable(userService.insertUser(user))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpServletRequest request) {
        System.out.println("user.toString() = " + user.toString());
        Map<String, Object> res = new HashMap<>();
        userService.insertUser(user);

        String token = jwtUtil.createToken(user.getId());
        res.put("user", user);
        res.put("access-token", token);
        res.put("message", SUCCESS);

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/token-check")
    public ResponseEntity<Void> checkTokenValidity(String token) {
        if (jwtUtil.isValid(token))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}