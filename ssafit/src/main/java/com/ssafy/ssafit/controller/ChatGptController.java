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
    //chat-gpt 와 간단한 채팅 서비스 소스
    @GetMapping ("/gpt")
    public String test(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }
}