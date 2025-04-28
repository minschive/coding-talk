package com.mycom.codingtalk.service;

import com.mycom.codingtalk.entity.Chatroom;
import com.mycom.codingtalk.exception.CustomException;
import com.mycom.codingtalk.mapper.ChatroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomMapper chatroomMapper;

    @Transactional
    public Chatroom createChatroom(String roomName, Integer userId) {
        Chatroom chatroom = new Chatroom();
        chatroom.setName(roomName);

        chatroomMapper.insertChatroom(chatroom);

        return getChatroomDetails(chatroom.getId());
    }

    public List<Map<String, Object>> getAllChatrooms() {
        return chatroomMapper.selectAllChatrooms();
    }

    public Chatroom getChatroomDetails(Integer chatroomId) {
        Chatroom chatroom = chatroomMapper.selectChatroomById(chatroomId);

        if (chatroom == null) {
            throw new CustomException("채팅방을 찾을 수 없습니다.");
        }

        return chatroom;
    }

}
