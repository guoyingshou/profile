package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;
import com.tissue.core.dao.UserDao;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PlanDao;
import com.tissue.plan.dao.PostDao;
import com.tissue.social.Activity;
import com.tissue.social.command.InvitationCommand;
import com.tissue.social.dao.ActivityDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class OwnerService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    @Resource(name="postDaoImpl")
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

    public List<Plan> getPlans(String userId) {
        return planDao.getPlansByUser(userId);
    }

    public long getPostsCount(String userId) {
        return postDao.getPostsCountByUser(userId);
    }

    public List<Post> getPagedPosts(String userId, int page, int size) {
        return postDao.getPagedPostsByUser(userId, page, size);
    }
 
    /**
    public boolean isInvitable(String ownerId, Account viewerAccount) {

        if((viewerAccount == null) || viewerAccount.hasRole("ROLE_EVIL")) {
            return false;
        }

        boolean invitable = false;
        if(!ownerId.equals(viewerAccount.getUser().getId())) {
            int inviteLimit = viewerAccount.getUser().getInviteLimit();
            if(inviteLimit > 0) {
                invitable = userDao.isInvitable(ownerId, viewerAccount);
            }
        }
        return invitable;
    }
    */
}
