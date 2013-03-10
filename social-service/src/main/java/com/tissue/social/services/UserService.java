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
import com.tissue.core.social.dao.UserDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public String addUser(UserCommand userCommand) {
        return userDao.create(userCommand);
    }

    public void updateProfile(ProfileCommand command) {
        userDao.updateProfile(command);
    }

    public void updateEmail(EmailCommand command) {
        userDao.updateEmail(command);
    }

    public void updatePassword(PasswordCommand command) {
        userDao.updatePassword(command);
    }

    public boolean isUsernameExist(String username) {
        return userDao.isUsernameExist(username);
    }

    public boolean isEmailExist(String email) {
        return userDao.isEmailExist(email);
    }

    public boolean isEmailExist(String excludingUserId, String email) {
        return userDao.isEmailExist(excludingUserId, email);
    }

    public Account getAccount(String accountId) {
        return userDao.getAccount(accountId);
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

    /**
    public List<User> getNewUsers(String excludingUserId, int limit) {
        return userDao.getNewUsers(excludingUserId, limit);
    }
    */

    public Boolean isFriend(String userId, Account viewerAccount) {
        if(viewerAccount == null) {
            return false;
        }
        return userDao.isFriend(userId, viewerAccount.getUser().getId());
    }

    public List<User> getFriends(String userId) {
        return userDao.getFriends(userId);
    }

    public List<Activity> getActivities(String userId, int count) {
        return userDao.getActivities(userId, count);
    }

    /**
     * topic
     */
    public List<Topic> getNewTopics(String excludingUserId, int limit) {
        return userDao.getNewTopics(excludingUserId, limit);
    }

    /**
     * plan
     */
    public List<Plan> getPlans(String userId) {
        return userDao.getPlans(userId);
    }

    public List<Plan> getPlansByAccount(String accountId) {
        return userDao.getPlansByAccount(accountId);
    }

    /**
     * post
     */
    public long getPostsCount(String userId) {
        return userDao.getPostsCount(userId);
    }

    public List<Post> getPagedPosts(String userId, int page, int size) {
        return userDao.getPagedPosts(userId, page, size);
    }
 
}
