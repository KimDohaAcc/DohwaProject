package com.ssafy.ssafit.service.mealService;


import com.ssafy.ssafit.domain.Meal;
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
        return mealRepository.save(meal);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Meal> getMealByUser(String user) {
        return mealRepository.findAllByUserOrderByNumDesc(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Meal> getMealAllByUser(String user) {
        return mealRepository.findAllByUser(user);
    }


    @Override
    public void removeMeal(String user) {
        List<Meal> meals = mealRepository.findByUserOrderByNumDesc(user);
        if (!meals.isEmpty()) {
            mealRepository.delete(meals.get(0));
        }
    }
}
