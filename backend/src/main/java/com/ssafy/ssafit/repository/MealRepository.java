package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUserOrderByNumDesc(String user);

    List<Meal> findByUserOrderByNumDesc(String user);

    List<Meal> findAllByUser(String user);
}
