package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.service.mealService.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MealController {
    private final MealService mealService;
//
//    @PostMapping("/meal")
//    ResponseEntity<List<Meal>> getUserMeal(@RequestBody User user){
//        return new ResponseEntity<>(mealService.getMealAllByUser(user), HttpStatus.OK);
//    }
}
