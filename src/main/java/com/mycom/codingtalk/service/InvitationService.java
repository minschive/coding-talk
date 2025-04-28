package com.mycom.codingtalk.service;

import com.mycom.codingtalk.entity.Invitation;
import com.mycom.codingtalk.entity.RoomMember;
import com.mycom.codingtalk.exception.CustomException;
import com.mycom.codingtalk.mapper.ChatroomMapper;
import com.mycom.codingtalk.mapper.InvitationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private InvitationMapper invitationMapper;

    @Autowired
    private ChatroomMapper chatroomMapper;

    @Transactional
    public Invitation createInvitation(Integer chatroomId, Integer inviterId, Integer inviteeId) {

        Invitation invitation = new Invitation();
        invitation.setChatroomId(chatroomId);
        invitation.setInviterId(inviterId);
        invitation.setInviteeId(inviteeId);
        invitation.setStatus("pending");

        invitationMapper.insertInvitation(invitation);

        return invitationMapper.selectInvitationById(invitation.getId());
    }

    @Transactional
    public Invitation respondToInvitation(Integer invitationId, String response, Integer userId) {
        Invitation invitation = invitationMapper.selectInvitationById(invitationId);
        if (invitation == null) {
            throw new CustomException("초대장을 찾을 수 없습니다.");
        }

        if (!invitation.getInviteeId().equals(userId)) {
            throw new CustomException("이 초대장에 응답할 권한이 없습니다.");
        }

        if (!"pending".equals(invitation.getStatus())) {
            throw new CustomException("이미 응답한 초대장입니다.");
        }

        if ("accept".equals(response)) {
            RoomMember member = new RoomMember();
            member.setUserId(userId);
            member.setChatroomId(invitation.getChatroomId());
            member.setRole("일반");

            chatroomMapper.insertRoomMember(member);
            invitationMapper.updateInvitationStatus(invitationId, "accepted");

        } else if ("reject".equals(response)) {
            invitationMapper.updateInvitationStatus(invitationId, "rejected");

        } else {
            throw new CustomException("잘못된 응답입니다. 'accept' 또는 'reject'로 응답해야 합니다.");
        }

        return invitationMapper.selectInvitationById(invitationId);
    }

    public List<Invitation> getReceivedInvitations(Integer userId) {
        return invitationMapper.selectInvitationsByInviteeId(userId);
    }

    public Invitation getInvitationDetails(Integer invitationId) {
        Invitation invitation = invitationMapper.selectInvitationById(invitationId);
        if (invitation == null) {
            throw new CustomException("초대장을 찾을 수 없습니다.");
        }
        return invitation;
    }
}
