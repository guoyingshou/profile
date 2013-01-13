package com.tissue.social.service.impl;

import com.tissue.core.social.Invitation;
import com.tissue.core.social.dao.InvitationDao;
import com.tissue.social.service.InvitationService;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component("invitationService")
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationDao invitationDao;

    public void inviteFriend(String fromId, String toId, String content) {
        invitationDao.inviteFriend(fromId, toId, content);
    }

    public void declineInvitation(String invitationId) {
        invitationDao.declineInvitation(invitationId);
    }

    public void acceptInvitation(String invitationId) {
        invitationDao.acceptInvitation(invitationId);
    }

}
