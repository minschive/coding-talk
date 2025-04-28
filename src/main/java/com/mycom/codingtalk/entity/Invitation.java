package com.mycom.codingtalk.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Invitation {
    private Integer id;
    private Integer chatroomId;
    private Integer inviterId;
    private Integer inviteeId;
    private String status;
    private LocalDateTime invitedAt;
    private LocalDateTime respondedAt;

    private String chatroomName;
    private String inviterName;
    private String inviteeName;
}
