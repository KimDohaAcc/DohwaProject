package com.ssafy.ssafit.service.kakaoService;

import java.io.IOException;
import java.util.Map;

public interface KaKaoService {
    String getToken(String code) throws IOException;

    Map<String, Object> getUserInfo(String accessToken) throws IOException;
}
