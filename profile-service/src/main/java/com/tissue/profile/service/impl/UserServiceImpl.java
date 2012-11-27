package com.tissue.profile.service.impl;

import com.tissue.profile.service.UserService;
import com.tissue.profile.dao.UserDao;
import com.tissue.profile.dao.DuplicateEmailException;
import com.tissue.domain.profile.User;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User addUser(User user) {
        User result = userDao.create(user);
        return result;
    }

    public User updateUser(User user) {
        User result = userDao.update(user);
        return result;
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

    /**
    public void getEdges(String fromId, String toId) {
        userDao.getEdges(fromId, toId);
    }
    */
}
