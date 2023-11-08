package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Admin;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.adminService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<Admin> createAdmin(Admin admin) {
        return Optional.ofNullable(adminService.insertAdmin(admin))
                .map(createdAdmin -> new ResponseEntity<>(createdAdmin, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/admin/check")
    public Optional<Admin> checkAdmin(User user) {
        return adminService.findAdminByUser(user);
    }
}
