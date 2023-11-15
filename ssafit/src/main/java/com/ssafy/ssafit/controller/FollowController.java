package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.Like;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.followService.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<Follow> startFollow(@RequestBody Follow follow) {
        return Optional.ofNullable(followService.createFollow(follow))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/follow")
    public ResponseEntity<List<Follow>> getFollowByFollower(@RequestBody User user, String type) {
        if (type.equals("follower"))
            return Optional.ofNullable(followService.getFollowByFollower(user))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        else
            return Optional.ofNullable(followService.getFollowByFollowee(user))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/follow")
    public ResponseEntity<Void> deleteFollow(@RequestBody Follow follow) {
        followService.removeFollow(follow);
        return ResponseEntity.noContent().build();
    }
}
