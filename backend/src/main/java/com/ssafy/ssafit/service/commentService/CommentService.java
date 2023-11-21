package com.ssafy.ssafit.service.commentService;

import com.ssafy.ssafit.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {


    //댓글 목록 조회
    Optional<List<Comment>> getCommentsByBoard(Long boardNum);

    //댓글 작성
    Comment writeComment(Comment comment);

    //댓글 조회
    Optional<Comment> getComment(Long userId);

    //댓글 수정
    void modifyComment(Comment comment);

    //댓글 삭제 기능
    boolean removeComment(Long commentId);



}
