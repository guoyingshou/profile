package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.social.Invitation;
import com.tissue.core.social.Activity;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.social.services.ActivityService;
import com.tissue.social.web.model.AccountForm;

import org.springframework.stereotype.Controller;
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

import com.google.common.hash.Hashing;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @ModelAttribute("viewer")
    public User initViewer(Map model) {
        String viewerId = SecurityUtil.getViewerId();
        if(viewerId == null) {
            return null;    
        }
        User viewer = userService.getUser(viewerId);
        List<Plan> plans = userService.getPlans(viewerId);
        model.put("plans", plans);
        return viewer;
    }

    @ModelAttribute("invitationsReceived")
    public List<Invitation> getInvitationsReceived() {
        String userId = SecurityUtil.getViewerId();
        if(userId == null) {
            return null;
        }
        return userService.getInvitationsReceived(userId);
    }

    /**
    @ModelAttribute("newTopics")
    public List<Topic> initTopics(Map model) {
        String viewerId = SecurityUtil.getViewerId();
        return userService.getNewTopics(viewerId, 10);
    }
    */

    @RequestMapping(value="/signout")
    public String signout(HttpSession ses, HttpServletRequest req, HttpServletResponse res, Map model) {

        model.put("viewer", null);
        List<Activity> activities = activityService.getActivitiesForNewUser(15);
        model.put("activities", activities);
 
        ses.invalidate();
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                res.addCookie(cookie);
            }
        }
       return "signout";
    }

    @RequestMapping(value="/home")
    public String index(Map model) {
        if(SecurityUtil.getViewerId() != null) {
            return "redirect:dashboard";
        }
        List<Activity> activities = activityService.getActivitiesForNewUser(15);
        model.put("activities", activities);
        return "home";
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model) {

        String userId = SecurityUtil.getViewerId();
        List<Activity> activities = activityService.getWatchedActivities(userId, 35);
        model.put("activities", activities);

        return "dashboard";
    }

    /**
    @RequestMapping(value="/watchedfeeds")
    public String watchedfeeds(Map model) {

        List<Activity> activities = activityService.getWatchedActivities(SecurityUtil.getViewerId(), 35);
        model.put("activities", activities);

        return "dashboard";
    }
    */

    @RequestMapping(value="/allfeeds")
    public String allfeeds(Map model) {

        List<Activity> activities = activityService.getActivities(25);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/invitations", method=GET)
    public String getInvitations() {
        return "dashboard";
    }

}
