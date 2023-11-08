package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Meal;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUser(User user);

    @Query("DELETE FROM Meal WHERE num = (SELECT num FROM Meal WHERE user = :user ORDER BY num DESC LIMIT 1)")
    void deleteMealByTime(@Param("user") User user);
}
