package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.service.boardService.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/{num}")
    public ResponseEntity<Board> detail(@PathVariable Long num) {
        return boardService.getBoard(num)
                .map(board -> new ResponseEntity<>(board, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/board")
    public ResponseEntity<List<Board>> getBoardAll() {
        return new ResponseEntity<>(boardService.getList(), HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<Board> write(@RequestBody Board board) {
        System.out.println("board = " + board);
        return Optional.ofNullable(boardService.writeBoard(board))
                .map(createBoard -> new ResponseEntity<>(createBoard, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
        System.out.println("board = " + board);
        boardService.modifyBoard(board);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/board/search")
    public ResponseEntity<List<Board>> write(@RequestParam String key, @RequestParam String word, @RequestParam String orderBy, @RequestParam String orderByDir) {
        List<Board> list = boardService.getListByCondition(key, word, orderBy, orderByDir);
        System.out.println("list = " + list);
        for (Board board : list) {
            System.out.println("board.toString() = " + board.toString());
        }
       return new ResponseEntity<>(list, HttpStatus.OK);
    }
}