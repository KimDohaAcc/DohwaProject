package com.ssafy.ssafit.service.likeService;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    @Override
    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like getLikeByUserAndVideo(User user, Video video) {
        return likeRepository.findByUserAndVideo(user, video);
    }

    @Override
    public List<Like> getLikeByUser(User user) {
        return likeRepository.findAllByUser(user);
    }

    @Transactional
    @Override
    public void removeLike(Like like) {
        likeRepository.delete(like);
    }
}
