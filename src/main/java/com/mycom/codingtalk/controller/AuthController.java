package com.mycom.codingtalk.controller;

import com.mycom.codingtalk.dto.request.UserRequestDto;
import com.mycom.codingtalk.dto.response.UserResponseDto;
import com.mycom.codingtalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto dto) {
        userService.register(dto);
        return "회원가입 성공!";
    }

    @PostMapping("/login")
    public Object login(@RequestBody UserRequestDto dto) {
        UserResponseDto response = userService.login(dto);
        if(response != null) {
            return response;
        } else {
            return "로그인 실패";
        }
    }

}
