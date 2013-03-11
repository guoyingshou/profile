package com.tissue.social.services;

import com.tissue.core.command.UserCommand;
import com.tissue.core.command.ProfileCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;
import com.tissue.core.command.InvitationCommand;
import com.tissue.core.command.ImpressionCommand;
import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.social.Impression;
import com.tissue.core.social.Invitation;
import com.tissue.core.social.Activity;
import com.tissue.core.social.About;
import com.tissue.core.social.dao.InvitationDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class InvitationService {

    @Autowired
    private InvitationDao invitationDao;

    public void inviteFriend(InvitationCommand command) {
        invitationDao.create(command);
    }

    public Invitation getInvitation(String invitationId) {
        return invitationDao.getInvitation(invitationId);
    }

    public List<Invitation> getInvitationsReceived(String userId) {
        return invitationDao.getInvitationsReceived(userId);
    }

    public void acceptInvitation(Invitation invitation) {
        invitationDao.acceptInvitation(invitation);
    }

    public void declineInvitation(Invitation invitation) {
        invitationDao.declineInvitation(invitation);
    }

    public Boolean isInvitable(String ownerId, Account viewerAccount) {
        return invitationDao.isInvitable(ownerId, viewerAccount);
    }

}
