package com.mycom.codingtalk.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomMember {
    private Integer id;
    private Integer userId;
    private Integer chatroomId;
    private String role;
    private LocalDateTime joinedAt;

}
