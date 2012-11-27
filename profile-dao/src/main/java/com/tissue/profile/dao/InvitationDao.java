package com.tissue.profile.dao;

import com.tissue.domain.profile.Invitation;
import java.util.List;

public interface InvitationDao {

    boolean canInvite(String userId1, String userId2);

    void inviteFriend(String fromId, String toId, String content);

    int getInvitationsCount(String userId);

    List<Invitation> getInvitations(String userId);

    boolean declineInvitation(String invitationId);

    boolean acceptInvitation(String invitationId);

}
