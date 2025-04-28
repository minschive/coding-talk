package com.mycom.codingtalk.mapper;

import com.mycom.codingtalk.entity.Chatroom;
import com.mycom.codingtalk.entity.RoomMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatroomMapper {
    void insertChatroom(Chatroom chatroom);

    List<Map<String, Object>> selectAllChatrooms();

    Chatroom selectChatroomById(@Param("chatroomId") Integer chatroomId);

    void insertRoomMember(RoomMember member);

}
