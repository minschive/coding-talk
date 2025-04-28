package com.mycom.codingtalk.mapper;

import com.mycom.codingtalk.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findByEmail(String email);
}
