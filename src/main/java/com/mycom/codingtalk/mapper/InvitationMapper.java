package com.mycom.codingtalk.mapper;

import com.mycom.codingtalk.entity.Invitation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationMapper {
    void insertInvitation(Invitation invitation);

    List<Invitation> selectInvitationsByInviteeId(@Param("inviteeId") Integer inviteeId);

    Invitation selectInvitationById(@Param("id") Integer id);

    void updateInvitationStatus(@Param("id") Integer id, @Param("status") String status);

}
