package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.service.videoService.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/video/{id}")
    public ResponseEntity<Video>detail(@PathVariable Long id){
        return videoService.getVideo(id)
                .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/video")
    public ResponseEntity<List<Video>> getVideoAll(){
        return new ResponseEntity<>(videoService.getVideoList(), HttpStatus.OK);
    }
}
