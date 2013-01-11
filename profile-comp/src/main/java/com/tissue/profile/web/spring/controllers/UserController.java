package com.tissue.profile.web.spring.controllers;

import com.tissue.core.social.Activity;
import com.tissue.core.profile.User;
import com.tissue.core.profile.Impression;
import com.tissue.core.profile.Invitation;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.profile.web.model.UserForm;
import com.tissue.profile.web.model.AccountForm;
import com.tissue.profile.service.UserService;
import com.tissue.profile.service.InvitationService;
import com.tissue.plan.service.PostService;
import com.tissue.commons.social.service.ActivityService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;

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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PostService postService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @ModelAttribute("viewer")
    public UserDetailsImpl getViewer() {
        return SecurityUtil.getViewer();
    }

    @RequestMapping(value="/home")
    public String index(Map model, Locale locale, @ModelAttribute("viewer") UserDetailsImpl viewer) {

        if(viewer == null) {
            //model.put("events", events);
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

        List<Invitation> invitations = invitationService.getInvitations(viewerId);
        model.put("invitationsCount", invitations.size());

        return "dashboard";
    }

    @RequestMapping(value="/users/{id}")
    public String getCNA(@PathVariable("id") String id, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        model.put("owner", userService.getUserById(id));

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByUserId(id);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);


        /**
        String viewerId = SecurityUtil.getViewerId();
        if(viewerId != null) {
            boolean canInvite = invitationService.canInvite(viewerId, id);
            model.put("canInvite", canInvite);
            model.put("viewer", userService.getUserById(viewerId));
        }
        */

        List<Post> posts = postService.getPagedPostsByUserId(id, page, size);
        model.put("posts", posts);

        return "user";
    }

    @RequestMapping(value="/users/{uid}/feed")
    public String getFeed(@PathVariable("uid") String uid, Map model) {

        model.put("owner", userService.getUserById(uid));

        List<Activity> activities = activityService.getUserActivities(uid, 15);
        model.put("activities", activities);

        return "feed";
    }

    @RequestMapping(value="/users/{uid}/resume", method=GET)
    public String getResume(@PathVariable("uid") String uid, Map model) {
        model.put("owner", userService.getUserById(uid));
        return "resume";
    }

    @RequestMapping(value="/users/{uid}/impressions")
    public String getImpression(@PathVariable("uid") String uid, Map model) {
        model.put("owner", userService.getUserById(uid));

        List<Impression> impressions = userService.getImpressions(uid);
        model.put("impressions", impressions);
        return "impression";
    }

    @RequestMapping(value="/users/{id}/invites")
    public String showInvitationForm(@PathVariable("id") String id, Map model) {

        if(!invitationService.canInvite(SecurityUtil.getViewerId(), id)) {
            return "redirect:/users/" + id;
        }

        //model.put("viewer", SecurityUtil.getViewer());
        model.put("owner", userService.getUserById(id));
        return "inviteForm";
    }

    @RequestMapping(value="/users/{id}/invites", method=POST)
    public String processInvitation(@PathVariable("id") String id, @RequestParam("content") String content, Map model) {

        invitationService.inviteFriend(SecurityUtil.getViewerId(), id, content);

        model.put("owner", userService.getUserById(id));
        return "redirect:/users/" + id;
    }

    /**
     * Get viewer's friends.
     */
    @RequestMapping(value="/friends")
    public String getFriends(Map model, @ModelAttribute("viewer") UserDetailsImpl viewer) {

        if(viewer != null) {
            List<User> friends = userService.getFriends(viewer.getId());
            model.put("friends", friends);
        }

        return "friends";
    }

}


