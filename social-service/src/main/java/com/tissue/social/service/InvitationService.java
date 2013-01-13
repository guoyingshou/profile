package com.tissue.social.service;

import com.tissue.core.social.Invitation;
import com.tissue.core.social.dao.DuplicateEmailException;
import java.util.List;

public interface InvitationService {

    void inviteFriend(String fromId, String toId, String content);

    void declineInvitation(String invitationid);

    void acceptInvitation(String invitationid);

}
