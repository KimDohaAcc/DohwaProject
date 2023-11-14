package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.service.videoService.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/video/{id}")
    public ResponseEntity<Video>detail(@PathVariable Long id){
        return videoService.getVideo(id)
                .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // 내일 할 일 : 비디오 리스트 만들기

    @PostMapping("/video")
    public ResponseEntity<Video> write(@RequestBody Video video){
        System.out.println("video = " + video);
        return Optional.ofNullable(videoService.writeVideo(video))
                .map(createVideo -> new ResponseEntity<>(createVideo, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/video/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(videoService.removeVideo(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/video/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Video updateVideo){
        Video newVideo = videoService.modifyVideo(id,updateVideo);
        if(newVideo != null){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
