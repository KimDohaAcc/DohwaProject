package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.service.userService.UserService;
import com.ssafy.ssafit.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/user")
    public ResponseEntity<User> regist(@RequestBody User userdto) {
        User user = new User(userdto.getId(), userdto.getNickname(), userdto.getAccount(), userdto.getPassword(), userdto.isIskakao());
        System.out.println("user = " + user);
        return Optional.ofNullable(userService.insertUser(user))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/dupCheck/{account}")
    public ResponseEntity<User> getUser(@PathVariable String account) {
        System.out.println("account = " + account);
        return userService.findUserByAccount(account)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        userService.insertUser(user);
        String token = jwtUtil.createToken(user.getId());
        res.put("user", user);
        res.put("access-token", token);
        res.put("message", SUCCESS);

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    @PostMapping("/originalLogin")
    public ResponseEntity<Map<String, Object>> originalLogin(@RequestParam("account") String account,
                                                             @RequestParam("password") String password) {
        Map<String, Object> res = new HashMap<>();
          return userService.findUserByAccount(account)
                    .map(user -> {
                        if(user.getPassword().equals(password)) {
                            String token = jwtUtil.createToken(user.getId());
                            res.put("user", user);
                            res.put("access-token", token);
                            res.put("message", SUCCESS);
                            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
                        }
                        res.put("message", FAIL);
                        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
                    })
                  .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<Void> unregisterUser(@PathVariable Long id, HttpServletRequest request) {
        String sessionToken = request.getHeader("Authorization");
        System.out.println("sessionToken = " + sessionToken);
        try {
            Optional<User> user = userService.findUserById(jwtUtil.extractUserIdFromToken(sessionToken));

            System.out.println("user = " + user);
            if (user.isPresent()) {
                // 사용자가 존재하는 경우에만 삭제를 시도
                userService.deleteUserById(id);

                // 토큰 체크 및 무효화
                if (sessionToken != null && sessionToken.startsWith("Bearer ")) {
                    sessionToken = sessionToken.substring(7); // "Bearer " 부분 제거
                    jwtUtil.invalidateToken(sessionToken);
                } else {
                    // 토큰이 없거나 형식이 맞지 않는 경우 UNAUTHORIZED 반환
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

                // 사용자 삭제 및 토큰 무효화 성공 시 OK 반환
                return ResponseEntity.ok().build();
            } else {
                // 사용자가 존재하지 않는 경우 NOT_FOUND 반환
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // 예외 발생 시 UNAUTHORIZED 반환
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }



    @GetMapping("/token-check")
    public ResponseEntity<Void> checkTokenValidity(String token) {
        if (jwtUtil.isValid(token))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}