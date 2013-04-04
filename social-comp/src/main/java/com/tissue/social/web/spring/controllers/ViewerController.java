package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.social.Invitation;
import com.tissue.social.Impression;
import com.tissue.social.Activity;
import com.tissue.plan.Plan;
import com.tissue.plan.Topic;
import com.tissue.social.web.model.ImpressionForm;
import com.tissue.social.services.ViewerService;
import com.tissue.social.services.AccountService;
import com.tissue.social.services.InvitationService;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.Set;
import java.util.Map;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ViewerController {

    private static Logger logger = LoggerFactory.getLogger(ViewerController.class);

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        List<Plan> plans = viewerService.getPlans(viewerAccount.getId());
        model.put("plans", plans);

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount);
        model.put("invitationsReceived", invitations);
 
        List<Activity> activities = viewerService.getWatchedActivities(viewerAccount.getUser().getId(), 35);
        model.put("activities", activities);

        model.put("selected", "watchedFeeds");
        return "dashboard";
    }

    @RequestMapping(value="/allfeeds")
    public String allfeeds(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        List<Plan> plans = viewerService.getPlans(viewerAccount.getId());
        model.put("plans", plans);

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount);
        model.put("invitationsReceived", invitations);
 
        List<Activity> activities = viewerService.getActivities(32);
        model.put("activities", activities);

        model.put("selected", "allFeeds");
        return "dashboard";
    }

    /**
     * Get viewer's friends.
     * In this case, viewer is the same as owner.
     */
    @RequestMapping(value="/friends")
    public String getFriends(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        List<Plan> plans = viewerService.getPlans(viewerAccount.getId());
        model.put("plans", plans);

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount);
        model.put("invitationsReceived", invitations);
 
        List<User> friends = viewerService.getFriends(viewerAccount.getUser().getId());
        model.put("friends", friends);

        model.put("selected", "friends");
        return "friends";
    }

    @RequestMapping(value="/friends/{friendId}/_remove", method=POST)
    public String removeFriend(@PathVariable("friendId") User friend, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        viewerService.removeRelation(friend.getId(), viewerAccount.getUser().getId());
        return "redirect:/friends";
    }


}
