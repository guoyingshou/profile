package com.tissue.social.web.spring.controllers;

import com.tissue.core.About;
import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.social.web.model.InvitationForm;
import com.tissue.social.web.model.ImpressionForm;
import com.tissue.core.social.Activity;
import com.tissue.core.social.Impression;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.services.CommonService;
import com.tissue.social.services.ActivityService;
import com.tissue.social.services.OwnerService;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class OwnerController {

    private static Logger logger = LoggerFactory.getLogger(OwnerController.class);

    @Autowired
    protected OwnerService ownerService;

    @Autowired
    protected ActivityService activityService;

    private User getOwner(String userId,  Account viewerAccount) {
        User owner = null;
        if((viewerAccount != null) && userId.equals(viewerAccount.getUser().getId())) {
            owner = viewerAccount.getUser();
        }
        else {
            owner = ownerService.getOwner(userId);
        }
        return owner;
    }

    private Boolean isOwnerInvitable(String userId, Account viewerAccount) {

        Boolean invitable = false;
        if((viewerAccount != null) && !userId.equals(viewerAccount.getUser().getId())) {
             invitable = ownerService.isInvitable(userId, viewerAccount);
        }
        return invitable;
    }

    private List<Plan> getPlans(String userId) {
        return ownerService.getOwnersPlans(userId);
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
        long total = ownerService.getOwnersPostsCount(userId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = ownerService.getOwnersPagedPosts(userId, page, size);
        model.put("posts", posts);

        model.put("selected", "posts");
        return "posts";
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

        List<Activity> activities = ownerService.getOwnersActivities(userId, 15);
        model.put("activities", activities);

        model.put("selected", "status");
        return "status";
    }

    @RequestMapping(value="/users/{userId}/impressions")
    public String getImpressions(@PathVariable("userId") String userId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        userId = "#" + userId;

        User owner = getOwner(userId, viewerAccount);
        model.put("owner", owner);

        Boolean invitable = isOwnerInvitable(userId, viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = getPlans(userId);
        model.put("plans", plans);

        Boolean isFriend = ownerService.isFriend(userId, viewerAccount);
        model.put("isFriend", isFriend);

        List<Impression> impressions = ownerService.getImpressions(userId);
        model.put("impressions", impressions);

        model.put("selected", "impressions");
        return "impressions";
    }

}


