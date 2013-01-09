package com.tissue.profile.service;

import com.tissue.core.profile.User;
import com.tissue.core.profile.dao.DuplicateEmailException;
import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserById(String id);
    User updateUser(User user);

    boolean isFriend(String userId1, String userId2);
    List<User> getFriends(String viewerId);

    //void getEdges(String fromId, String toId);
}
