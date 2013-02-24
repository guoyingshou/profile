package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.Activity;
import com.tissue.core.social.User;
import com.tissue.core.social.Impression;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.social.services.ActivityService;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.social.web.model.AccountForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Locale;


@Controller
public class UserController {

    @Autowired
    protected UserService userService;

    @Autowired
    private ActivityService activityService;

    private User init(String userId, Map model) {
        List<Plan> plans = userService.getPlans(userId);
        model.put("plans", plans);

        String viewerAccountId = SecurityUtil.getViewerAccountId();
        User viewer = null;
        if(viewerAccountId != null) {
             viewer = userService.getUserByAccount(viewerAccountId);
             model.put("viewer", viewer);

             Boolean invitable = false;
             User owner;
             if(!userId.equals(viewer.getId())) {
                 invitable = userService.isInvitable(viewer.getId(), userId);
                 owner = userService.getUser(userId);
             }
             else {
                 owner = viewer;
             }
             model.put("invitable", invitable);
             model.put("owner", owner);
        }
        return viewer;
    }

    /**
    @ModelAttribute("newTopics")
    public List<Topic> initTopics(Map model) {
        String viewerId = SecurityUtil.getViewerId();
        return userService.getNewTopics(viewerId, 10);
    }
    */

    @RequestMapping(value="/users/{userId}/posts")
    public String getCNA(@PathVariable("userId") String userId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        userId = "#" + userId;
        init(userId, model);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = userService.getPostsCount(userId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = userService.getPagedPosts(userId, page, size);
        model.put("posts", posts);

        return "user";
    }

    @RequestMapping(value="/users/{userId}/status")
    public String getFeed(@PathVariable("userId") String userId, Map model) {

        userId = "#" + userId;
        init(userId, model);

        List<Activity> activities = userService.getUserActivities(userId, 15);
        model.put("activities", activities);

        return "user";
    }

    @RequestMapping(value="/users/{userId}/resume", method=GET)
    public String getResume(@PathVariable("userId") String userId, Map model) {
        userId = "#" + userId;
        init(userId, model);
        return "user";
    }

    @RequestMapping(value="/users/{userId}/impressions")
    public String getImpression(@PathVariable("userId") String userId, Map model) {

        userId = "#" + userId;
        User viewer = init(userId, model);

        Boolean isFriend = userService.isFriend(viewer.getId(), userId);
        model.put("isFriend", isFriend);

        List<Impression> impressions = userService.getImpressions(userId);
        model.put("impressions", impressions);

        return "user";
    }

    /**
     * Get viewer's friends.
     * In this case, viewer is the same as owner.
     */
    @RequestMapping(value="/users/{userId}/friends")
    public String getFriends(@PathVariable("userId") String userId, Map model) {

        userId = "#" + userId;
        init(userId, model);

        List<User> friends = userService.getFriends(userId);
        model.put("friends", friends);

        return "user";
    }
}


