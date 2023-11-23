package com.ssafy.ssafit.service.commentService;

import com.ssafy.ssafit.domain.Comment;
import com.ssafy.ssafit.repository.CommentRepository;
import com.ssafy.ssafit.service.boardService.BoardService;
import com.ssafy.ssafit.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BoardService boardService;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Comment>> getCommentsByBoard(Long boardNum) {
        return boardService.getBoard(boardNum).map(commentRepository::findAllByBoard);
    }

    @Override
    public Comment writeComment(Comment comment) {
        comment.setUser(userService.findUserById(comment.getUser().getId()).get());
        comment.setBoard(boardService.getBoard(comment.getBoard().getNum()).get());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getComment(Long userId) {
        return commentRepository.findById(userId);
    }

    @Override
    public void modifyComment(Comment comment) {
        commentRepository.save(comment);

    }

    @Override
    public boolean removeComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        } else {
            return false;
        }

    }
}
