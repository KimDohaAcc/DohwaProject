package com.ssafy.ssafit.service.videoService;

import com.ssafy.ssafit.domain.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    List<Video> getVideoList();

    Video writeVideo(Video video);

    Optional<Video> getVideo(Long id);

    Video modifyVideo(Long id, Video video);

    boolean removeVideo(Long id);

    List<Video> getListByCondition(String key, String word);
}
