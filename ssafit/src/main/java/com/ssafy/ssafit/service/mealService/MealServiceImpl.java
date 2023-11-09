package com.ssafy.ssafit.service.mealService;


import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    @Transactional
    @Override
    public Meal createMeal(Meal meal) {
        System.out.println("meal = " + meal.toString());
        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> getMealByUser(User user) {
        return mealRepository.findAllByUserOrderByNumDesc(user);
    }

    @Transactional
    @Override
    public void removeMeal(User user) {
        List<Meal> meals = mealRepository.findByUserOrderByNumDesc(user);
        System.out.println("meals = " + meals);
        if (!meals.isEmpty()) {
            mealRepository.delete(meals.get(0));
        }
    }
}
