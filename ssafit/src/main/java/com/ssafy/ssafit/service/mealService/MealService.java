package com.ssafy.ssafit.service.mealService;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;

import java.util.List;

public interface MealService {
    Meal createMeal(Meal meal);

    List<Meal> getMealByUser(User user);

    void removeMeal(User user);
}
