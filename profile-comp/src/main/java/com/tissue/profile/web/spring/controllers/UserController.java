package com.tissue.profile.web.spring.controllers;

import com.tissue.domain.social.Event;
import com.tissue.domain.profile.User;
import com.tissue.domain.profile.Invitation;
import com.tissue.domain.plan.Post;
import com.tissue.profile.web.model.UserForm;
import com.tissue.profile.web.model.AccountForm;
import com.tissue.profile.service.UserService;
import com.tissue.profile.service.InvitationService;
import com.tissue.plan.service.PostService;
import com.tissue.commons.service.EventService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.security.core.userdetails.UserDetailsImpl;
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
    private EventService eventService;

    @Autowired
    private PostService postService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @RequestMapping(value="/home")
    public String index(Map model, Locale locale) {

        UserDetailsImpl viewer = SecurityUtil.getUser();

        if(viewer == null) {
            //List<Event> events = eventService.getLatestEvents(25);
            //model.put("events", events);
            return "home";
        }
        else {
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, Locale locale) {

        String viewerId = SecurityUtil.getUserId();
        //List<Event> events = eventService.getTopicRelatedEvents(viewerId, 25);
        //model.put("events", events);

        List<Invitation> invitations = invitationService.getInvitations(viewerId);
        model.put("invitationsCount", invitations.size());

        model.put("viewer", SecurityUtil.getUser());
        return "dashboard";
    }

    @RequestMapping(value="/users/{id}")
    public String getCNA(@PathVariable("id") String id, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByUserId(id);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);


        String viewerId = SecurityUtil.getUserId();
        if(viewerId != null) {
            boolean canInvite = invitationService.canInvite(viewerId, id);
            model.put("canInvite", canInvite);
            model.put("viewer", userService.getUserById(viewerId));
        }
        model.put("owner", userService.getUserById(id));

        List<Post> posts = postService.getPagedPostsByUserId(id, page, size);
        model.put("posts", posts);

        return "cna";
    }

    @RequestMapping(value="/users/{id}/invites")
    public String showInvitationForm(@PathVariable("id") String id, Map model) {

        if(!invitationService.canInvite(SecurityUtil.getUserId(), id)) {
            return "redirect:/users/" + id;
        }

        model.put("viewer", SecurityUtil.getUser());
        model.put("owner", userService.getUserById(id));
        return "inviteForm";
    }

    @RequestMapping(value="/users/{id}/invites", method=POST)
    public String processInvitation(@PathVariable("id") String id, @RequestParam("content") String content, Map model) {

        invitationService.inviteFriend(SecurityUtil.getUserId(), id, content);

        model.put("viewer", SecurityUtil.getUser());
        model.put("owner", userService.getUserById(id));
        return "redirect:/users/" + id;
    }

    /**
     * Get viewer's friends.
     */
    @RequestMapping(value="/friends")
    public String getFriends(Map model) {

        String viewerId = SecurityUtil.getUserId();

        if(viewerId != null) {
            List<User> friends = userService.getFriends(viewerId);
            model.put("friends", friends);
        }

        model.put("viewer", SecurityUtil.getUser());
        return "friends";
    }


    @RequestMapping(value="/actions")
    public String getCNA(Map model) {
        String viewerId = SecurityUtil.getUserId();

        List<Event> events = eventService.getFriendsEvents(viewerId, 25);
        model.put("events", events);

        List<Invitation> invitations = invitationService.getInvitations(viewerId);
        model.put("invitationsCount", invitations.size());

        model.put("viewer", SecurityUtil.getUser());
        return "actions";
    }


    /**
    @RequestMapping(value="/users/{id}/messages")
    public String showMessageForm(@PathVariable("id") String id, Map model) {

        model.put("viewer", SecurityUtil.getUser());
        model.put("owner", userService.getUserById(id));
        return "messageForm";
    }

    @RequestMapping(value="/users/{id}/message", method=POST)
    public String sendMessage(@PathVariable("id") String id, @RequestParam("content") String content, Map model) {
        model.put("viewer", SecurityUtil.getUser());
        model.put("owner", userService.getUserById(id));
        return "redirect:/profile/users/" + id;
    }
    */

}


