package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.social.Invitation;
import com.tissue.social.dao.InvitationDao;
import com.tissue.social.command.InvitationCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class InvitationService {

    @Autowired
    private InvitationDao invitationDao;

    public void createInvitation(InvitationCommand command) {
        invitationDao.create(command);
    }

    public Invitation getInvitation(String invitationId) {
        return invitationDao.getInvitation(invitationId);
    }

    /**
    public List<Invitation> getInvitationsReceived(Account account) {
        return invitationDao.getInvitationsReceived(account.getUser().getId());
    }
    */

    public List<Invitation> getViewerReceivedInvitations() {
        return invitationDao.getInvitationsReceived(SecurityUtil.getViewerAccountId());
    }
 
    public void acceptInvitation(Invitation invitation) {
        invitationDao.acceptInvitation(invitation);
    }

    public void declineInvitation(Invitation invitation) {
        invitationDao.declineInvitation(invitation);
    }

}
