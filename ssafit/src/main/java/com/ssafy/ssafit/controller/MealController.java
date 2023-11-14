package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.mealService.MealService;
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

    @GetMapping("/meal")
    ResponseEntity<List<Meal>> getUserMeal(@ModelAttribute User user){
        return new ResponseEntity<>(mealService.getMealAllByUser(user), HttpStatus.OK);
    }
}
