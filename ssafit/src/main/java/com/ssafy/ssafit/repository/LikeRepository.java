package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByUserAndVideo(User user, Video video);

    List<Like> findAllByUser(User user);
}
