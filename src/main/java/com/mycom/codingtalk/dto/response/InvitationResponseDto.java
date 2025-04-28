package com.mycom.codingtalk.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvitationResponseDto {
    private Integer id;
    private Integer chatroomId;
    private Integer inviterId;
    private Integer inviteeId;
    private String status;
    private String chatroomName;
    private String inviterName;
    private String inviteeName;
    private LocalDateTime invitedAt;
    private LocalDateTime respondedAt;
}
