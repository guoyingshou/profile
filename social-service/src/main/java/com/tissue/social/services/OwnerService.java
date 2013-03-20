package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.ProfileCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;
import com.tissue.core.dao.UserDao;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.plan.dao.PlanDao;
import com.tissue.core.plan.dao.PostDao;
import com.tissue.core.social.Activity;
import com.tissue.core.social.Impression;
import com.tissue.core.social.command.InvitationCommand;
import com.tissue.core.social.command.ImpressionCommand;
import com.tissue.core.social.dao.ImpressionDao;
import com.tissue.core.social.dao.ActivityDao;
import com.tissue.core.social.dao.InvitationDao;

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
    private InvitationDao invitationDao;

    @Autowired
    private ImpressionDao impressionDao;

    @Autowired
    private ActivityDao activityDao;

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

   
    public List<Activity> getActivities(String userId, int count) {
        return activityDao.getSelfActivities(userId, count);
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
 
    public List<Impression> getImpressions(String userId) {
        return impressionDao.getImpressions(userId);
    }

    /**
    public Boolean isInvitable(String ownerId, Account viewerAccount) {
        return invitationDao.isInvitable(ownerId, viewerAccount);
    }
    */

    public void checkInvitable(User owner, Account viewerAccount, Map model) {
        Boolean invitable = false;
        if((viewerAccount != null) && !owner.getId().equals(viewerAccount.getUser().getId())) {
            invitable = invitationDao.isInvitable(owner, viewerAccount);
        }
        model.put("invitable", invitable);
    }


}
