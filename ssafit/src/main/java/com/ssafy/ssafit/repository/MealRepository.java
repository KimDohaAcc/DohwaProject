package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUserOrderByNumDesc(User user);

    List<Meal> findByUserOrderByNumDesc(User user);

    List<Meal> findAllByUser(User user);
}
