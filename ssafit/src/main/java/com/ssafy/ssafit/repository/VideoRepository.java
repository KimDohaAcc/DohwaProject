package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
