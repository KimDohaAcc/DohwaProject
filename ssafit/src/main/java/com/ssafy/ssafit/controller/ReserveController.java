package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.reserveService.ReserveService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping("/reserve/create")
    public void createReserve(@RequestParam Reserve reserve) {
        reserveService.createReserve(reserve);
    }

    @PutMapping("/reserve/update")
    public void updateReserve(@RequestParam Reserve reserve) {
        reserveService.modifyReserve(reserve);
    }

    @DeleteMapping("/reserve/delete")
    public void deleteReserve(@RequestParam Reserve reserve) {
        reserveService.deleteReserve(reserve);
    }

    @GetMapping("/reserve/get")
    public ResponseEntity<List<Reserve>> getReserveByUser(HttpSession session) {
        Optional<List<Reserve>> reserveList = Optional.ofNullable((User) session.getAttribute("loginUser"))
                .map(reserveService::getListByUser);
        return reserveList.map(list -> new ResponseEntity<List<Reserve>>(list, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}