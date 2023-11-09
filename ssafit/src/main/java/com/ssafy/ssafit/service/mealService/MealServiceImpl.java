package com.ssafy.ssafit.service.mealService;


import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Override
    public Meal createMeal(Meal meal) {
        System.out.println("meal = " + meal.toString());
        return mealRepository.save(meal);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Meal> getMealByUser(User user) {
        return mealRepository.findAllByUserOrderByNumDesc(user);
    }


    @Override
    public void removeMeal(User user) {
        List<Meal> meals = mealRepository.findByUserOrderByNumDesc(user);
        System.out.println("meals = " + meals);
        if (!meals.isEmpty()) {
            mealRepository.delete(meals.get(0));
        }
    }
}
