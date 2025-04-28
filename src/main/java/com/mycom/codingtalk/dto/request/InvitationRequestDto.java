package com.mycom.codingtalk.dto.request;

import lombok.Data;

@Data
public class InvitationRequestDto {
    private Integer chatroomId;
    private Integer inviterId;
    private Integer inviteeId;
}
