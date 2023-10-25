package com.ssafy.ssafit.service.userService;

import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.dto.UserDto;
import com.ssafy.ssafit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;

    //    public void insertUser(User user){
//        userRepository.save(user);
//    }
    // CRUD
    /*
    insert
    update
    find
    delete
     */

    // 카카오로그인시 db에 유저로 저장 메소드
    // userRepository
    private final UserRepository userRepository;

    @Override
    public void insertUser(UserDto userDto) {
        Optional<User> userTemp = userRepository.findById(userDto.getId());

        if(userTemp.isEmpty()){
            User user = new User(userDto);
            userRepository.save(user);
        }
    }

    @Override
    public Optional<User> findUserById(Long id){
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()){
           return null;
       }

       else {
           return user;
       }
    }
}
