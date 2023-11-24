package com.ssafy.ssafit.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorHandleController implements ErrorController {

    @GetMapping("/")
    public String redirect() {
        return "index.html";
    }

    @GetMapping("/error")
    public String getErrorPath() {
        return "redirect:/";
    }
}
