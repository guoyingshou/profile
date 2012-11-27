package com.tissue.profile.service;

import com.tissue.domain.profile.Invitation;
import com.tissue.profile.dao.DuplicateEmailException;
import java.util.List;

public interface InvitationService {

    boolean canInvite(String fromId, String toId);

    void inviteFriend(String fromId, String toId, String content);

    int getInvitationsCount(String viewerId);

    List<Invitation> getInvitations(String viewerId);

    boolean declineInvitation(String invitationid);

    boolean acceptInvitation(String invitationid);

}
