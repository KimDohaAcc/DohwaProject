package com.ssafy.ssafit.service.likeService;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.domain.Video;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Like createLike(Like like);
    Optional<Like> getLikeByUserAndVideo(User user, Video video);

    List<Like> getLikeByUser(User user);

    void removeLike(Like like);

    int getLikeCountByVideo(Long videoNum);
}
