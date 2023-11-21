package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Comment;
import com.ssafy.ssafit.service.commentService.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment/{num}")
    public ResponseEntity<Comment> detail(@PathVariable Long num) {
        return commentService.getComment(num)
                .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/comment/board/{boardNum}")
    public ResponseEntity<?> getCommentByBoard(@PathVariable Long boardNum) {
        return commentService.getCommentsByBoard(boardNum)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> write(@RequestBody Comment comment) {
        System.out.println("comment = " + comment.toString());
        return Optional.ofNullable(commentService.writeComment(comment))
                .map(createComment -> new ResponseEntity<>(createComment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/comment/{num}")
    public ResponseEntity<Void> delete(@PathVariable Long num) {
        if (commentService.removeComment(num)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/comment")
    public ResponseEntity<Void> update(@RequestBody Comment comment) {
        commentService.modifyComment(comment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
