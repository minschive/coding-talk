package com.mycom.codingtalk.service;

import com.mycom.codingtalk.common.utiil.PasswordUtil;
import com.mycom.codingtalk.dto.request.UserRequestDto;
import com.mycom.codingtalk.dto.response.UserResponseDto;
import com.mycom.codingtalk.entity.User;
import com.mycom.codingtalk.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void register(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(PasswordUtil.hashPassword(dto.getPassword()));
        user.setName(dto.getName());
        userMapper.insertUser(user);
    }

    public UserResponseDto login(UserRequestDto dto) {
        User user = userMapper.findByEmail(dto.getEmail());
        if(user != null && PasswordUtil.checkPassword(dto.getPassword(), user.getPassword())) {
            return new UserResponseDto(user.getId(), user.getEmail(), user.getName(), "로그인 성공");
        }
        return null;
    }
}
