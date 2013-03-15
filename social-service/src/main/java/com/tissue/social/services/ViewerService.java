package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.social.Invitation;
import com.tissue.core.social.Activity;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.dao.UserDao;
import com.tissue.core.dao.AccountDao;
import com.tissue.core.social.dao.InvitationDao;
import com.tissue.core.social.dao.ImpressionDao;
import com.tissue.core.social.dao.ActivityDao;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.plan.dao.PlanDao;
import com.tissue.core.plan.dao.PostDao;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.ProfileCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;
import com.tissue.core.command.InvitationCommand;
import com.tissue.core.command.ImpressionCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ViewerService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InvitationDao invitationDao;

    @Autowired
    private ImpressionDao impressionDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    @Autowired
    private PostDao postDao;


    public void updateEmail(EmailCommand command) {
        accountDao.updateEmail(command);
    }

    public void updatePassword(PasswordCommand command) {
        accountDao.updatePassword(command);
    }

    public void updateProfile(ProfileCommand command) {
        userDao.updateProfile(command);
    }

    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    public User getUserByAccount(String accountId) {
        return userDao.getUserByAccount(accountId);
    }

    public String getUserIdByAccount(String accountId) {
        return userDao.getUserIdByAccount(accountId);
    }

    public List<Activity> getWatchedActivities(String userId, int count) {
        return activityDao.getWatchedActivities(userId, count);
    }

    public List<Activity> getActivities(int num) {
        return activityDao.getActivities(num);
    }
 
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

    public Boolean isFriend(String userId, Account viewerAccount) {
        if(viewerAccount == null) {
            return false;
        }
        return userDao.isFriend(userId, viewerAccount.getUser().getId());
    }

    public List<User> getFriends(String userId) {
        return userDao.getFriends(userId);
    }

    public List<Activity> getSelfActivities(String userId, int count) {
        return activityDao.getSelfActivities(userId, count);
    }

    public List<Topic> getNewTopics(String excludingUserId, int limit) {
        return topicDao.getNewTopics(excludingUserId, limit);
    }

    public List<Plan> getPlansByUser(String userId) {
        return planDao.getPlansByUser(userId);
    }

    public List<Plan> getPlansByAccount(String accountId) {
        return planDao.getPlansByAccount(accountId);
    }

    public long getPostsCountByUser(String userId) {
        return postDao.getPostsCountByUser(userId);
    }

    public List<Post> getPagedPostsByUser(String userId, int page, int size) {
        return postDao.getPagedPostsByUser(userId, page, size);
    }
 
    public void addImpression(ImpressionCommand command) {
        impressionDao.addImpression(command);
    }

}
