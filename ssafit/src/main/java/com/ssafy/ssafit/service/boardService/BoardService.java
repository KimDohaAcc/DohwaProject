package com.ssafy.ssafit.service.boardService;

import com.ssafy.ssafit.domain.Board;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Board> getList();

    Board writeBoard(Board board);

    Optional<Board> getBoard(Long id);

    void modifyBoard(Board board);

    boolean removeBoard(Long id);
}
