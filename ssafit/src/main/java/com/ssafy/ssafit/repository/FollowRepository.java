package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFollower(User user);
    List<Follow> findAllByFollowee(User user);
}
