package com.ssafy.ssafit.service.userService;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    String getToken(String code) throws IOException;

    Map<String, Object> getUserInfo(String accessToken) throws IOException;
}
