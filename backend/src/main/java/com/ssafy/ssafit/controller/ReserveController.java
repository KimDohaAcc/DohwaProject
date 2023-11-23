package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.reserveService.ReserveService;
import com.ssafy.ssafit.service.userService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.ssafit.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class ReserveController {
    private final ReserveService reserveService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/reserve/create")
    public ResponseEntity<Object> createReserve(@RequestBody Reserve reserve, HttpServletRequest request) {
        String sessionToken = request.getHeader("Authorization");
        try {
            Optional<User> user = userService.findUserById(jwtUtil.extractUserIdFromToken(sessionToken));

            if (user.isPresent()) {
                reserveService.createReserve(reserve);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return null;
    }

    @DeleteMapping("/reserve/delete/{id}")
    public void deleteReserve(@PathVariable Long id, HttpServletRequest request) {

        String sessionToken = request.getHeader("Authorization");
        try {
            Optional<User> user = userService.findUserById(jwtUtil.extractUserIdFromToken(sessionToken));

            if (user.isPresent()) {
                reserveService.deleteReserve(id);
            } else {
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/reserve/get/{id}")
    public Object getReserveByUser(@PathVariable Long id, HttpServletRequest request) {
        String sessionToken = request.getHeader("Authorization");
        try {
            Optional<User> user = userService.findUserById(jwtUtil.extractUserIdFromToken(sessionToken));

            List<Reserve> tempList = new ArrayList<Reserve>();
            if (user.isPresent()) {
                tempList = reserveService.getListByUser(user);
                return new ResponseEntity<>(tempList, HttpStatus.OK);
            } else {
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            }
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return null;
    }


}