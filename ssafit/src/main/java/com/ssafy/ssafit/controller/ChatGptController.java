package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatGptController {
    private final ChatService chatService;
    @GetMapping ("/gpt")
    public String test(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }
}