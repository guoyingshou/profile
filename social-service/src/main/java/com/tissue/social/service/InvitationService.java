package com.tissue.social.service;

import com.tissue.core.orient.dao.DuplicateEmailException;
import com.tissue.core.social.Invitation;
import java.util.List;

public interface InvitationService {

    void inviteFriend(String fromId, String toId, String content);

    void declineInvitation(String invitationid);

    void acceptInvitation(String invitationid);

}
