package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

        List<Comment> findAllByBoardNum(Long boardNum);

}
