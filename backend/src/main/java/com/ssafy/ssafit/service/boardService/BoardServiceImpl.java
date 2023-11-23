package com.ssafy.ssafit.service.boardService;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Override
    public Board writeBoard(Board board) {
        System.out.println("board = " + board);
        return boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Board> getBoard(Long num) {
        return boardRepository.findById(num);
    }


    @Override
    public void modifyBoard(Board board) {
        boardRepository.save(board);
    }


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
    @Transactional(readOnly = true)
    public List<Board> getListByCondition(String key, String word) {
        return boardRepository.getListByCondition(key, word);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getListByUser(User user) {
        return boardRepository.getBoardsByUser(user);
    }
}
