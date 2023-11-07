package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
