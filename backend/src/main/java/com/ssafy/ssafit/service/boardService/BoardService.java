package com.ssafy.ssafit.service.boardService;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.User;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Board> getList();
    List<Board> getListByUser(User user);

    Board writeBoard(Board board);

    Optional<Board> getBoard(Long id);

    void modifyBoard(Board board);

    boolean removeBoard(Long id);

    List<Board> getListByCondition(String key, String word, String orderBy, String orderByDir);
}
