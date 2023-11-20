package com.ssafy.ssafit.service.boardService;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Transactional
    @Override
    public Board writeBoard(Board board) {
        System.out.println("board = " + board);
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> getBoard(Long num) {
        return boardRepository.findById(num);
    }

    @Transactional
    @Override
    public void modifyBoard(Board board) {
        System.out.println("board = " + board);
        boardRepository.save(board);
    }


    @Transactional
    @Override
    public boolean removeBoard(Long num) {
        if (boardRepository.existsById(num)) {
            boardRepository.deleteById(num);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Board> getListByCondition(String key, String word, String orderBy, String orderByDir) {
        return boardRepository.getListByCondition(key, word, orderBy, orderByDir);
    }
}
