package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.followService.FollowService;
import com.ssafy.ssafit.service.userService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/follow")
    public ResponseEntity<?> startFollow(@RequestBody User user, HttpServletRequest request) {
        return userService.extractUserFromToken(request.getHeader("Authorization"))
                .map(follower -> {
                    if (user.getId().equals(follower.getId())) {
                        return new ResponseEntity<String>("자기 자신은 팔로우 할 수 없습니다!", HttpStatus.NO_CONTENT);
                    }

                    if (followService.getFollowByFollowerAndFollowee(follower, user).isPresent()) {
                        return new ResponseEntity<String>("이미 팔로우 중입니다!", HttpStatus.NO_CONTENT);
                    }
                    return new ResponseEntity<Follow>(followService.createFollow(new Follow(null, follower, user)), HttpStatus.CREATED);
                }).orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/follow")
    public ResponseEntity<List<Follow>> getFollowByFollower(HttpServletRequest request) {
        return userService.extractUserFromToken(request.getHeader("Authorization"))
                .map(follower ->
                        new ResponseEntity<>(followService.getFollowByFollower(follower), HttpStatus.OK)
                )
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/followee")
    public ResponseEntity<List<Follow>> getFollowByFollowee(HttpServletRequest request) {
        return userService.extractUserFromToken(request.getHeader("Authorization"))
                .map(followee ->
                        new ResponseEntity<>(followService.getFollowByFollowee(followee), HttpStatus.OK)
                )
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/follow/delete")
    public ResponseEntity<Void> deleteFollow(@RequestBody User user, HttpServletRequest request) {
        System.out.println("user = " + user);
        return userService.extractUserFromToken(request.getHeader("Authorization"))
                .map(follower -> {
                    followService.getFollowByFollowerAndFollowee(follower, user)
                            .ifPresent(followService::removeFollow);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
