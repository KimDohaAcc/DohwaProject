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
        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> getMealByUser(User user) {
        return mealRepository.findAllByUser(user);
    }

    @Transactional
    @Override
    public void removeMeal(User user) {
        mealRepository.deleteMealByTime(user);
    }
}
