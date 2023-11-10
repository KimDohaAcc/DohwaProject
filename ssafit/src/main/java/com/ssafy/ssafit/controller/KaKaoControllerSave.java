//package com.ssafy.ssafit.controller;
//
//import com.ssafy.ssafit.domain.User;
//import com.ssafy.ssafit.service.kakaoService.KaKaoService;
//import com.ssafy.ssafit.service.userService.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Controller
//@RequiredArgsConstructor
//public class KaKaoControllerSave {
//
//    private final KaKaoService ks;
//    private final UserService userService;
//
//    @GetMapping("/do")
//    public String loginPage() {
//
//        return "kakaoCI/login";
//    }
//
//    @GetMapping("/member/kakao")
//    public String getCI(@RequestParam String code, Model model, HttpServletRequest request) throws IOException {
//        System.out.println("code = " + code);
//        String access_token = ks.getToken(code);
//        Map<String, Object> userInfo = ks.getUserInfo(access_token);
//        model.addAttribute("code", code);
//        model.addAttribute("access_token", access_token);
//        model.addAttribute("userInfo", userInfo);
//        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
//
//        // 카카오에서 로그인 완료
//
//        // 카카오service 에서 db 넣어주기
//        // id, nickname, email
//        User user = new User((Long) userInfo.get("id"), (String) userInfo.get("nickname"), (String) userInfo.get("email"));
//        userService.insertUser(user);
//        HttpSession session = request.getSession();
//        session.setAttribute("loginUser", user);
//        // kakaoService.createUser(model.id, model.nickname, model.account)
//        return "index";
//    }
//
//    @GetMapping("/member/do")
//    public String logout() {
//        return "index";
//    }
//
//
//}
