package com.ssafy.ssafit.service.commentService;

import com.ssafy.ssafit.domain.Comment;
import com.ssafy.ssafit.repository.CommentRepository;
import com.ssafy.ssafit.service.boardService.BoardService;
import com.ssafy.ssafit.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BoardService boardService;

    @Transactional
    @Override
    public Comment writeComment(Comment comment) {
        comment.setUser(userService.findUserById(comment.getUser().getId()).get());
        comment.setBoard(boardService.getBoard(comment.getBoard().getNum()).get());
        System.out.println(comment);
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getComment(Long userId) {
        return commentRepository.findById(userId);
    }

    @Transactional
    @Override
    public void modifyComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public boolean removeComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<List<Comment>> getCommentAllByBoard(Long boardNum) {
        return boardService.getBoard(boardNum)
                .map(commentRepository::findAllByBoard);
    }
}