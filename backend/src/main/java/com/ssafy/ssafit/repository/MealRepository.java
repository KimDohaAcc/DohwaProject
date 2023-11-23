package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUserOrderByNumDesc(String user);

    List<Meal> findByUserOrderByNumDesc(String user);

    List<Meal> findAllByUser(String user);
}
