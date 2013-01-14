package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Activity;
import com.tissue.core.social.User;
import com.tissue.core.social.Impression;
import com.tissue.core.social.Invitation;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.social.service.ActivityService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.service.UserService;
import com.tissue.social.web.model.UserForm;
import com.tissue.social.web.model.AccountForm;
import com.tissue.social.service.InvitationService;
import com.tissue.plan.service.PostService;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.nio.charset.Charset;
import java.security.Principal;

import com.google.common.hash.Hashing;

@Controller
public class UserController extends ViewerSetter {

    /**
    @Autowired
    private UserService userService;
    */

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PostService postService;

    @RequestMapping(value="/home")
    public String index(Map model, Locale locale, @ModelAttribute("viewer") User viewer) {

        if(viewer == null) {
            return "home";
        }
        else {
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, Locale locale) {

        String viewerId = SecurityUtil.getViewerId();

        List<Activity> activities = activityService.getFriendsActivities(viewerId, 15);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/users/{id}")
    public String getCNA(@PathVariable("id") String id, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        model.put("owner", userService.getUserById(id, false));

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByUserId(id);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByUserId(id, page, size);
        model.put("posts", posts);

        return "user";
    }

    @RequestMapping(value="/users/{uid}/status")
    public String getFeed(@PathVariable("uid") String uid, Map model) {

        model.put("owner", userService.getUserById(uid, false));

        List<Activity> activities = activityService.getUserActivities(uid, 15);
        model.put("activities", activities);

        return "status";
    }

    @RequestMapping(value="/users/{uid}/resume", method=GET)
    public String getResume(@PathVariable("uid") String uid, Map model) {
        model.put("owner", userService.getUserById(uid, false));
        return "resume";
    }

    @RequestMapping(value="/users/{uid}/impressions")
    public String getImpression(@PathVariable("uid") String uid, Map model) {
        model.put("owner", userService.getUserById(uid, false));

        List<Impression> impressions = userService.getImpressions(uid);
        model.put("impressions", impressions);
        return "impression";
    }

    /**
     * Get viewer's friends.
     * In this case, viewer is the same as owner.
     */
    @RequestMapping(value="/users/{uid}/friends")
    public String getFriends(@PathVariable("uid") String uid, Map model, @ModelAttribute("viewer") User viewer) {
        if(viewer.isSelf(uid)) {
            model.put("owner", viewer);
        }
        else {
            model.put("owner", userService.getUserById(uid, true));
        }
        return "friends";
    }

    @RequestMapping(value="/invitations")
    public String getInvitations() {
        return "invitationList";
    }


}


