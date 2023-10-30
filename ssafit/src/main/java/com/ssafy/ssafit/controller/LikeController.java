package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.likeService.LikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like/create")
    public ResponseEntity<Like> clickLike(@RequestParam Like like) {
        Optional.ofNullable(likeService.getLikeByUserAndVideo(like.getUser(), like.getVideo()))
                .ifPresentOrElse(likeService::removeLike,
                        () -> likeService.createLike(like));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/like/list")
    public ResponseEntity<List<Like>> getLikeByUser(HttpSession session) {
        Optional<List<Like>> likeList = Optional.ofNullable((User) session.getAttribute("loginUser"))
                .map(likeService::getLikeByUser);

        return likeList.map(list -> new ResponseEntity<List<Like>>(list, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
