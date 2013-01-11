package com.tissue.profile.service.impl;

import com.tissue.core.profile.User;
import com.tissue.core.profile.Impression;
import com.tissue.core.profile.dao.UserDao;
import com.tissue.core.profile.dao.DuplicateEmailException;
import com.tissue.profile.service.UserService;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User addUser(User user) {
        return userDao.create(user);
    }

    public User updateUser(User user) {
        return userDao.update(user);
    }

    public void addResume(String userId, String resume) {
        userDao.addResume(userId, resume);
    }

    public void addImpression(Impression impression) {
        userDao.addImpression(impression);
    }

    public List<Impression> getImpressions(String userId) {
        return userDao.getImpressions(userId);
    }

    /**
     * @param userId user id
     */
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    public boolean isFriend(String userId1, String userId2) {
        return userDao.isFriend(userId1, userId2);
    }

    public List<User> getFriends(String viewerId) {
        return userDao.getFriends(viewerId);
    }

}
