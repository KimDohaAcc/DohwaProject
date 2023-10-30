package com.ssafy.ssafit.service.likeService;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.domain.Video;

import java.util.List;

public interface LikeService {
    Like createLike(Like like);

    Like getLikeByUserAndVideo(User user, Video video);

    List<Like> getLikeByUser(User user);

    void removeLike(Like like);
}
