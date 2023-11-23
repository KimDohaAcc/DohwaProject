package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.service.videoService.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/video/search")
    public ResponseEntity<List<Video>> search(@RequestParam String key, @RequestParam String word) {
        System.out.println("key = " + key);
        System.out.println("word = " + word);
        List<Video> list = videoService.getListByCondition(key, word);
        for (Video video : list) {
            System.out.println("video.toString() = " + video.toString());
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
