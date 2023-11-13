package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.service.mealService.MealService;
import com.ssafy.ssafit.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MealController {
    private final MealService mealService;
    private final UserService userService;

    @GetMapping("/meal")
    ResponseEntity<?> getUserMeal(String email){
        return userService.findUserByEmail(email)
                .map(mealService::getMealByUser)
                .map(list -> new ResponseEntity<List<Meal>>(list, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
