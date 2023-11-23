package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndVideo(User user, Video video);

    List<Like> findAllByUser(User user);

    int countLikeByVideo(Video video);
}
