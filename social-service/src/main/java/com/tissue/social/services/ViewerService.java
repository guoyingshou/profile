package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.dao.UserDao;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;
import com.tissue.social.Activity;
import com.tissue.social.dao.ActivityDao;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PlanDao;
import com.tissue.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ViewerService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    @Resource(name="postDaoImpl")
    private PostDao postDao;


    public void updateProfile(UserCommand command) {
        userDao.updateProfile(command);
    }

    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    public User getUserByAccount(String accountId) {
        return userDao.getUserByAccount(accountId);
    }

    public List<Activity> getWatchedActivities(String userId, int count) {
        return activityDao.getWatchedActivities(userId, count);
    }

    public List<Activity> getActivities(int num) {
        return activityDao.getActivities(num);
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

    public List<Plan> getPlans(Account account) {
        return planDao.getPlansByAccount(account.getId());
    }

    public long getPostsCountByUser(String userId) {
        return postDao.getPostsCountByUser(userId);
    }

    public List<Post> getPagedPostsByUser(String userId, int page, int size) {
        return postDao.getPagedPostsByUser(userId, page, size);
    }
 
}
