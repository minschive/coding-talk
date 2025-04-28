package com.mycom.codingtalk.controller;

import com.mycom.codingtalk.dto.response.InvitationResponseDto;
import com.mycom.codingtalk.entity.Invitation;
import com.mycom.codingtalk.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping("/rooms/{roomId}/invite")
    public ResponseEntity<InvitationResponseDto> createInvitation(
            @PathVariable String roomId,
            @RequestBody Map<String, Integer> request) {
        try {
            Integer inviteeId = request.get("inviteeId");
            Integer inviterId = request.get("userId");

            if(inviterId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Invitation invitation = invitationService.createInvitation(
                    Integer.valueOf(roomId),
                    inviterId,
                    inviteeId);

            InvitationResponseDto responseDto = convertToDto(invitation);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/invitations/{inviteId}/accept")
    public ResponseEntity<InvitationResponseDto> acceptInvitation(
            @PathVariable Integer inviteId,
            @RequestBody Map<String, Integer> request) {
        try {
            Integer userId = request.get("userId");

            if(userId == null ){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Invitation invitation = invitationService.respondToInvitation(inviteId, "accept", userId);

            InvitationResponseDto responseDto = convertToDto(invitation);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/invitations/{inviteId}/reject")
    public ResponseEntity<InvitationResponseDto> rejectInvitation(
            @PathVariable Integer inviteId,
            @RequestBody Map<String, Integer> request) {
        try {
            Integer userId = request.get("userId");

            if(userId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Invitation invitation = invitationService.respondToInvitation(inviteId, "reject", userId);

            InvitationResponseDto responseDto = convertToDto(invitation);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/invitations")
    public ResponseEntity<List<InvitationResponseDto>> getReceivedInvitations(
            @RequestParam Integer userId) { // URL 파라미터로 받음
        try {
            List<Invitation> invitations = invitationService.getReceivedInvitations(userId);
            List<InvitationResponseDto> responseDtos = invitations.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(responseDtos, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/invitations/{id}")
    public ResponseEntity<InvitationResponseDto> getInvitationDetails(@PathVariable Integer id) {
        try {
            Invitation invitation = invitationService.getInvitationDetails(id);
            InvitationResponseDto responseDto = convertToDto(invitation);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private InvitationResponseDto convertToDto(Invitation invitation) {
        if (invitation == null) {
            return null;
        }

        InvitationResponseDto dto = new InvitationResponseDto();
        dto.setId(invitation.getId());
        dto.setChatroomId(invitation.getChatroomId());
        dto.setInviterId(invitation.getInviterId());
        dto.setInviteeId(invitation.getInviteeId());
        dto.setStatus(invitation.getStatus());
        dto.setChatroomName(invitation.getChatroomName());
        dto.setInviterName(invitation.getInviterName());
        dto.setInviteeName(invitation.getInviteeName());
        dto.setInvitedAt(invitation.getInvitedAt());
        dto.setRespondedAt(invitation.getRespondedAt());

        return dto;
    }

    private <T> ResponseEntity<T> handleException(Exception e) {
        System.err.println("Error in InvitationController: " + e.getMessage());
        e.printStackTrace();

        return new ResponseEntity<>((T) null, HttpStatus.BAD_REQUEST);
    }
}