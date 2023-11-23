package com.ssafy.ssafit.service.mealService;

import com.ssafy.ssafit.domain.Meal;

import java.util.List;
public interface MealService {
    Meal createMeal(Meal meal);

    List<Meal> getMealByUser(String user);

    List<Meal> getMealAllByUser(String user);

    void removeMeal(String user);
}
