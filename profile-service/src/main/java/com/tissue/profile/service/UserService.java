package com.tissue.profile.service;

import com.tissue.core.profile.User;
import com.tissue.core.profile.Impression;
import com.tissue.core.profile.dao.DuplicateEmailException;
import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(User user);

    void addResume(String userId, String resume);

    void addImpression(Impression impression);

    List<Impression> getImpressions(String userId);

    User getUserById(String id);

    boolean isFriend(String userId1, String userId2);
    List<User> getFriends(String viewerId);

    //void getEdges(String fromId, String toId);
}
