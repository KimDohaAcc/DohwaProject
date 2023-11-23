package com.ssafy.ssafit.service.followService;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowServiceimpl implements FollowService {
    private final FollowRepository followRepository;

    @Override
    public Follow createFollow(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Follow> getFollowByFollower(User user) {
        return followRepository.findAllByFollower(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Follow> getFollowByFollowee(User user) {
        return followRepository.findAllByFollowee(user);
    }

    @Override
    public void removeFollow(Follow follow) {
        followRepository.delete(follow);
    }

    @Override
    public Optional<Follow> getFollowByFollowerAndFollowee(User follower, User followee) {
        return followRepository.findFollowByFollowerAndFollowee(follower, followee);
    }
}
