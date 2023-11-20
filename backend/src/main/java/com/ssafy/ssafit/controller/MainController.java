package com.ssafy.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/healthCheck")
    public ResponseEntity<Void> healthCheckForAWS(){
        return ResponseEntity.ok().build();
    }
}
