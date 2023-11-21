package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.reserveService.ReserveService;
import com.ssafy.ssafit.service.userService.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class ReserveController {
    private final ReserveService reserveService;
    private final UserService userService;

    @PostMapping("/reserve/create")
    public void createReserve(@RequestBody Reserve reserve) {
        reserveService.createReserve(reserve);
    }

//    @PutMapping("/reserve/update")
//    public void updateReserve(@RequestBody Reserve reserve) {
//        reserveService.modifyReserve(reserve);
//    }

    @DeleteMapping("/reserve/delete/{id}")
    public void deleteReserve(@PathVariable Long id) {
        reserveService.deleteReserve(id);
    }

    @GetMapping("/reserve/get/{id}")
    public ResponseEntity<List<Reserve>> getReserveByUser(@PathVariable Long id) {
        // 앞으로 request 받아와서 token에서 user id를 빼서 user 를 찾아올 것!
        System.out.println("?");
        User user = userService.findUserById(id).get();
        System.out.println(user.toString());
        System.out.println("리스트 가져오나?");
        List<Reserve> tempList = new ArrayList<Reserve>();
        if(user!=null){
            tempList = reserveService.getListByUser(user);
        }
        System.out.println(tempList);
        return new ResponseEntity<>(tempList,HttpStatus.OK);
//        Optional<List<Reserve>> reserveList = Optional.ofNullable((User) session.getAttribute("loginUser"))
//                .map(reserveService::getListByUser);
//        return reserveList.map(list -> new ResponseEntity<List<Reserve>>(list, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}