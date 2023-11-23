package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFollower(User user);

    List<Follow> findAllByFollowee(User user);

    Optional<Follow> findFollowByFollowerAndFollowee(User follower, User followee);
}
