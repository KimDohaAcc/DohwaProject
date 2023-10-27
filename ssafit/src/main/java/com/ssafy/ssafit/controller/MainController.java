package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.service.boardService.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final BoardService boardService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("boardPage")
    public String showBoardAll(Model model) {
        model.addAttribute("list", boardService.getList());
        return "/boardPage";
    }
}
