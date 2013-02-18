package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Activity;
import com.tissue.core.social.User;
import com.tissue.core.social.Impression;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerOwnerTopicSetter;
import com.tissue.commons.social.service.ActivityService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.service.UserService;
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

/**
 * Controller that present user related infomation.
 * All views returned from each handler method will need two data model named 'viewer' and 'owner',
 * which are setup in the superclass: ViewerOwnerTopicSetter.
 */
@Controller
public class UserController extends ViewerOwnerTopicSetter {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value="/users/{userId}/posts")
    public String getCNA(@PathVariable("userId") String userId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        userId = "#" + userId;

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = userService.getPostsCountByUserId(userId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = userService.getPagedPostsByUserId(userId, page, size);
        model.put("posts", posts);

        return "posts";
    }

    @RequestMapping(value="/users/{userId}/status")
    public String getFeed(@PathVariable("userId") String userId, Map model) {

        userId = "#" + userId;
        List<Activity> activities = activityService.getUserActivities(userId, 15);
        model.put("activities", activities);

        return "status";
    }

    @RequestMapping(value="/users/{userId}/resume", method=GET)
    public String getResume(@PathVariable("userId") String userId, Map model) {
        return "resume";
    }

    @RequestMapping(value="/users/{userId}/impressions")
    public String getImpression(@PathVariable("userId") String userId, Map model) {

        userId = "#" + userId;
        List<Impression> impressions = userService.getImpressions(userId);
        model.put("impressions", impressions);

        return "impressions";
    }

    /**
     * Get viewer's friends.
     * In this case, viewer is the same as owner.
     */
    @RequestMapping(value="/users/{userId}/friends")
    public String getFriends(@PathVariable("userId") String userId, Map model) {

        userId = "#" + userId;
        List<User> friends = userService.getFriends(userId);
        model.put("friends", friends);

        return "friends";
    }

}


