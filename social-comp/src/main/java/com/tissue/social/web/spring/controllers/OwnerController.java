package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.commons.services.ViewerService;
import com.tissue.social.Activity;
import com.tissue.social.Impression;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.commons.util.Pager;
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
    protected ViewerService viewerService;

    @Autowired
    protected ActivityService activityService;

    @Autowired
    protected OwnerService ownerService;

    @RequestMapping(value="/users/{userId}/posts")
    public String getPosts(@PathVariable("userId") User owner, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "posts");
        model.put("owner", owner);

        Boolean invitable = ownerService.isInvitable(owner.getId(), viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = ownerService.getPlans(owner.getId());
        model.put("plans", plans);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 12 : size;
        long total = ownerService.getPostsCount(owner.getId());
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = ownerService.getPagedPosts(owner.getId(), page, size);
        model.put("posts", posts);

        return "posts";
    }

    @RequestMapping(value="/users/{userId}/status")
    public String getFeed(@PathVariable("userId") User owner, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "status");
        model.put("owner", owner);

        Boolean invitable = ownerService.isInvitable(owner.getId(), viewerAccount);
        model.put("invitable", invitable);

        List<Plan> plans = ownerService.getPlans(owner.getId());
        model.put("plans", plans);

        List<Activity> activities = activityService.getActivitiesByUser(owner.getId(), 16);
        model.put("activities", activities);

        return "status";
    }

}


