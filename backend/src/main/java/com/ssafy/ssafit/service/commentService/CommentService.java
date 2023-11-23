package com.ssafy.ssafit.service.commentService;

import com.ssafy.ssafit.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {


    Optional<List<Comment>> getCommentsByBoard(Long boardNum);

    Comment writeComment(Comment comment);

    Optional<Comment> getComment(Long userId);

    void modifyComment(Comment comment);

    boolean removeComment(Long commentId);


}
