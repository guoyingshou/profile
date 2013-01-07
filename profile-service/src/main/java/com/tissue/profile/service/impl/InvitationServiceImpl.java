package com.tissue.profile.service.impl;

import com.tissue.profile.service.InvitationService;

//import com.tissue.domain.social.Event;
import com.tissue.domain.profile.Invitation;
import com.tissue.profile.dao.InvitationDao;
//import com.tissue.commons.dao.social.EventDao;
//import com.tissue.commons.util.EventFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component("invitationService")
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationDao invitationDao;

    /**
    @Autowired
    private EventDao eventDao;
    */

    public boolean canInvite(String fromId, String toId) {
        return invitationDao.canInvite(fromId, toId);
    }

    public void inviteFriend(String fromId, String toId, String content) {
        invitationDao.inviteFriend(fromId, toId, content);
    }

    /**
    public int getInvitationsCount(String viewerId) {
        return invitationDao.getInvitationsCount(viewerId);
    }
    */

    public List<Invitation> getInvitations(String userId) {
        return invitationDao.getInvitations(userId);
    }

    public boolean declineInvitation(String invitationId) {
        return invitationDao.declineInvitation(invitationId);
    }

    public boolean acceptInvitation(String invitationId) {
        Invitation invitation = invitationDao.acceptInvitation(invitationId);
        /**
        List<Event> events = EventFactory.createEvents(invitation);
        eventDao.addEvent(events.get(0), events.get(1));
        */
        return true;
    }

}
