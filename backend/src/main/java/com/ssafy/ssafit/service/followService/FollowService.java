package com.ssafy.ssafit.service.followService;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.User;

import java.util.List;
import java.util.Optional;

public interface FollowService {
    Follow createFollow(Follow follow);

    List<Follow> getFollowByFollower(User user);

    List<Follow> getFollowByFollowee(User user);

    Optional<Follow> getFollowByFollowerAndFollowee(User follower, User followee);

    void removeFollow(Follow follow);
}
