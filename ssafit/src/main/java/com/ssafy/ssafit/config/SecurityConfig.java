//package com.ssafy.ssafit.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecurityConfig {
////    @Value() 나중에 jwt 토큰 사용하기
//    @Bean
//    // Spring security 6.0 이상에서 많이 바뀜!
//    // 그래서 최신 코드 찾아보고 써야 한다~
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                 .authorizeRequests()
//                // 특정 API에 대해 모든 사용자에게 접근 허용
//                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**","/**").permitAll()
//                .anyRequest().permitAll();
//        http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
//}
