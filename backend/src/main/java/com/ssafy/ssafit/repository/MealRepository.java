package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUserOrderByNumDesc(String user);

    Optional<Meal> findTopByUserOrderByTimeDesc(String user);

    List<Meal> findAllByUser(String user);
}
