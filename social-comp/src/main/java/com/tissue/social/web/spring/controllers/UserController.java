package com.tissue.social.web.spring.controllers;

import com.tissue.core.command.Command;
import com.tissue.core.social.Account;
import com.tissue.core.social.Activity;
import com.tissue.core.social.User;
import com.tissue.core.social.Impression;
import com.tissue.core.social.About;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.services.CommonService;
import com.tissue.social.web.model.AccountForm;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected CommonService commonService;

    private User getOwner(String userId,  Account viewerAccount) {
        User owner = null;
        if(viewerAccount != null) {
             if(userId.equals(viewerAccount.getUser().getId())) {
                 owner = viewerAccount.getUser();
             }
             else {
                 owner = userService.getUser(userId);
             }
        }
        return owner;
    }

    private Boolean isOwnerInvitable(String userId, Account viewerAccount) {

        Boolean invitable = false;
        if((viewerAccount != null) && !userId.equals(viewerAccount.getUser().getId())) {
             invitable = userService.isInvitable(viewerAccount.getUser().getId(), userId);
        }
        return invitable;
    }

    private List<Plan> getPlans(String userId) {
        return userService.getPlans(userId);
    }

    @RequestMapping(value="/users/{userId}/posts")
    public String getCNA(@PathVariable("userId") String userId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        userId = "#" + userId;

        User owner = getOwner(userId, viewerAccount);
        model.put("owner", owner);

        Boolean invitable = isOwnerInvitable(userId, viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = getPlans(userId);
        model.put("plans", plans);

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
    public String getFeed(@PathVariable("userId") String userId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        userId = "#" + userId;

        User owner = getOwner(userId, viewerAccount);
        model.put("owner", owner);

        Boolean invitable = isOwnerInvitable(userId, viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = getPlans(userId);
        model.put("plans", plans);


        List<Activity> activities = userService.getUserActivities(userId, 15);
        model.put("activities", activities);

        return "user";
    }

    /**
    @RequestMapping(value="/users/{userId}/resume", method=GET)
    public String getResume(@PathVariable("userId") String userId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        userId = "#" + userId;
        User owner = getOwner(userId, viewerAccount);
        model.put("owner", owner);

        Boolean invitable = isOwnerInvitable(userId, viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = getPlans(userId);
        model.put("plans", plans);

        return "user";
    }
    */

    @RequestMapping(value="/users/{userId}/impressions")
    public String getImpression(@PathVariable("userId") String userId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        userId = "#" + userId;

        User owner = getOwner(userId, viewerAccount);
        model.put("owner", owner);

        Boolean invitable = isOwnerInvitable(userId, viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = getPlans(userId);
        model.put("plans", plans);

        Boolean isFriend = userService.isFriend(userId, viewerAccount.getId());
        model.put("isFriend", isFriend);

        List<Impression> impressions = userService.getImpressions(userId);
        model.put("impressions", impressions);

        return "user";
    }

}


