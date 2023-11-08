package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.service.mealService.MealService;
import com.ssafy.ssafit.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    @PostMapping("/meal")
    public ResponseEntity<Meal> createMeal(String reply) {
        String[] rep = reply.split("/");
        Long userId = Long.parseLong(rep[0]);
        String kcal = rep[1];
        String time = rep[2];

        return userService.findUserById(userId)
                .map(user -> new Meal(null, user, kcal, time))
                .map(mealService::createMeal)
                .map(createdMeal -> new ResponseEntity<>(createdMeal, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/meal/delete")
    public ResponseEntity<?> deleteMeal(String reply) {
        Long userId = Long.parseLong(reply);
        userService.findUserById(userId).ifPresent(mealService::removeMeal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
