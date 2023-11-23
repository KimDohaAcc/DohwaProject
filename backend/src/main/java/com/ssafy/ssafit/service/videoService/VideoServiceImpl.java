package com.ssafy.ssafit.service.videoService;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public List<Video> getVideoList() {
        return videoRepository.findAll();
    }

    @Override
    public Video writeVideo(Video video) {
        System.out.println("video = " + video);
        return videoRepository.save(video);
    }

    @Override
    public Optional<Video> getVideo(Long id) {
        return videoRepository.findById(id);
    }

    @Override
    public Video modifyVideo(Long id, Video video) {
        Optional<Video> existingVideo = videoRepository.findById(id);
        if (existingVideo.isPresent()) {
            videoRepository.save(video);
        }
        return null;
    }

    @Override
    public boolean removeVideo(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<Video> getListByCondition(String key, String word) {
        return videoRepository.getListByCondition(key, word);
    }
}
