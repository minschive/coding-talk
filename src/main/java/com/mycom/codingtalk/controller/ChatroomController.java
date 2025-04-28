package com.mycom.codingtalk.controller;

import com.mycom.codingtalk.dto.request.ChatroomRequestDto;
import com.mycom.codingtalk.dto.response.ChatroomResponseDto;
import com.mycom.codingtalk.entity.Chatroom;
import com.mycom.codingtalk.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class ChatroomController {

    private final ChatroomService chatroomService;

    @PostMapping
    public ResponseEntity<ChatroomResponseDto> createChatroom(@RequestBody ChatroomRequestDto requestDto) {
        try {
            Chatroom chatroom = chatroomService.createChatroom(requestDto.getName(), requestDto.getUserId());
            ChatroomResponseDto responseDto = convertToDto(chatroom);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllChatrooms() {
        try {
            List<Map<String, Object>> chatrooms = chatroomService.getAllChatrooms();
            return new ResponseEntity<>(chatrooms, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }


    @GetMapping("/{roomid}")
    public ResponseEntity<ChatroomResponseDto> getChatroomDetails(@PathVariable("roomid") Integer id) {
        try {
            Chatroom chatroom = chatroomService.getChatroomDetails(id);
            ChatroomResponseDto responseDto = convertToDto(chatroom);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ChatroomResponseDto convertToDto(Chatroom chatroom) {
        if (chatroom == null) {
            return null;
        }

        ChatroomResponseDto dto = new ChatroomResponseDto();
        dto.setId(chatroom.getId());
        dto.setName(chatroom.getName());
        dto.setCreatedAt(chatroom.getCreatedAt());
        dto.setUpdatedAt(chatroom.getUpdatedAt());

        return dto;
    }

    private <T> ResponseEntity<T> handleException(Exception e) {
        System.err.println("Error in ChatroomController: " + e.getMessage());
        e.printStackTrace();

        return new ResponseEntity<>((T) null, HttpStatus.BAD_REQUEST);
    }
}