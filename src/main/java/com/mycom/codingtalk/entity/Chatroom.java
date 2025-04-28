package com.mycom.codingtalk.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Chatroom {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
