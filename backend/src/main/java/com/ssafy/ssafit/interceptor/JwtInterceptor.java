package com.ssafy.ssafit.interceptor;

import com.ssafy.ssafit.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //OPTIONS 메서드에 대한 요청에 대해서는 CORS 관련 검사를 수행하지 않고, 다른 실제 요청에 대해서만 JWT 검증을 수행함
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("Authorization");

        if (jwtUtil.isValid(token)) {
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or Missing JWT Token");
            return false;
        }
    }
}