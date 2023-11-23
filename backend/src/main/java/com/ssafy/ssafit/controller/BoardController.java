package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.boardService.BoardService;
import com.ssafy.ssafit.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/board/{num}")
    public ResponseEntity<Board> detail(@PathVariable Long num) {
        return boardService.getBoard(num)
                .map(board -> new ResponseEntity<>(board, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/auth/board/{id}")
    public ResponseEntity<List<Board>> getBoardListByUserId(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(boardService::getListByUser)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/board")
    public ResponseEntity<List<Board>> getBoardAll() {
        return new ResponseEntity<>(boardService.getList(), HttpStatus.OK);
    }

    @PostMapping("/auth/board")
    public ResponseEntity<?> write(@RequestBody Board board) {
        return Optional.ofNullable(boardService.writeBoard(board))
                .map(createBoard -> new ResponseEntity<>(createBoard, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @DeleteMapping("/board/{num}")
    public ResponseEntity<Void> delete(@PathVariable Long num) {
        if (boardService.removeBoard(num)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/board")
    public ResponseEntity<Void> update(@RequestBody Board board) {
        boardService.modifyBoard(board);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/board/search")
    public ResponseEntity<List<Board>> search(@RequestParam String key, @RequestParam String word) {
        List<Board> list = boardService.getListByCondition(key, word);
        for (Board board : list) {
            System.out.println("board.toString() = " + board.toString());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}