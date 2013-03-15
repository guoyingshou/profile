package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.social.Activity;
import com.tissue.core.social.Impression;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.dao.UserDao;
import com.tissue.core.social.dao.ImpressionDao;
import com.tissue.core.social.dao.ActivityDao;
import com.tissue.core.social.dao.InvitationDao;
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
public class OwnerService {

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


    public User getOwner(String userId) {
        return userDao.getUser(userId);
    }

    public Boolean isFriend(String userId, Account viewerAccount) {
        if(viewerAccount == null) {
            return false;
        }
        return userDao.isFriend(userId, viewerAccount.getUser().getId());
    }

    /**
    public User getUserByAccount(String accountId) {
        return userDao.getUserByAccount(accountId);
    }

    public String getUserIdByAccount(String accountId) {
        return userDao.getUserIdByAccount(accountId);
    }

    public List<User> getFriends(String userId) {
        return userDao.getFriends(userId);
    }

    public List<Topic> getNewTopics(String excludingUserId, int limit) {
        return topicDao.getNewTopics(excludingUserId, limit);
    }
    */
    
    public List<Activity> getOwnersActivities(String userId, int count) {
        return activityDao.getSelfActivities(userId, count);
    }

    public List<Plan> getOwnersPlans(String userId) {
        return planDao.getPlansByUser(userId);
    }

    /**
    public List<Plan> getPlansByAccount(String accountId) {
        return planDao.getPlansByAccount(accountId);
    }
    */

    public long getOwnersPostsCount(String userId) {
        return postDao.getPostsCountByUser(userId);
    }

    public List<Post> getOwnersPagedPosts(String userId, int page, int size) {
        return postDao.getPagedPostsByUser(userId, page, size);
    }
 
    public List<Impression> getImpressions(String userId) {
        return impressionDao.getImpressions(userId);
    }

    public Boolean isInvitable(String ownerId, Account viewerAccount) {
        return invitationDao.isInvitable(ownerId, viewerAccount);
    }


}
