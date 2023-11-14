package com.ssafy.ssafit.controller;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2CustomAuthorizerEvent;
import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.mealService.MealService;
import com.ssafy.ssafit.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> regist(@RequestBody User user) {
        System.out.println("user = " + user);
        return Optional.ofNullable(userService.insertUser(user))
                .map(saveUser -> new ResponseEntity<>(saveUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(findUser -> new ResponseEntity<>(findUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return Optional.ofNullable(userService.insertUser(user))
                .map(registUser -> new ResponseEntity<>(registUser,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(user, HttpStatus.OK));
    }
}
