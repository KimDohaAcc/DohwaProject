package com.ssafy.ssafit.service.likeService;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.repository.LikeRepository;
import com.ssafy.ssafit.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final VideoRepository videoRepository;

    @Override
    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Optional<Like> getLikeByUserAndVideo(User user, Video video) {
        return likeRepository.findByUserAndVideo(user, video);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Like> getLikeByUser(User user) {
        return likeRepository.findAllByUser(user);
    }

    @Override
    public void removeLike(Like like) {
        likeRepository.delete(like);
    }

    @Override
    @Transactional(readOnly = true)
    public int getLikeCountByVideo(Long videoNum) {
        return videoRepository.findById(videoNum)
                .map(likeRepository::countLikeByVideo)
                .orElse(0);
    }
}
