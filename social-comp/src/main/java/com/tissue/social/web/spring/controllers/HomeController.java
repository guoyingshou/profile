package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.social.Invitation;
import com.tissue.core.social.Activity;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.social.services.InvitationService;
import com.tissue.commons.social.services.ActivityService;
import com.tissue.social.web.model.UserForm;
import com.tissue.social.web.model.ProfileForm;
import com.tissue.social.web.model.EmailForm;
import com.tissue.social.web.model.PasswordForm;
import com.tissue.social.web.model.InvitationForm;
//import com.tissue.social.web.model.ImpressionForm;

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
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private InvitationService invitationService;

    private void init(Account viewerAccount, Map model) {
        List<Plan> plans = userService.getPlansByAccount(viewerAccount.getId());
        model.put("plans", plans);

        model.put("owner", viewerAccount.getUser());

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount.getUser().getId());
        model.put("invitationsReceived", invitations);
    }

    @RequestMapping(value="/signout")
    public String signout(HttpSession ses, HttpServletRequest req, HttpServletResponse res, Map model) {

        model.put("viewerAccount", null);

        List<Activity> activities = activityService.getActivitiesForNewUser(35);
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

        if(SecurityUtil.getViewerAccountId() != null) {
            return "redirect:dashboard";
        }

        List<Activity> activities = activityService.getActivitiesForNewUser(35);
        model.put("activities", activities);

        return "home";
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        init(viewerAccount, model);

        List<Activity> activities = userService.getActivities(viewerAccount.getUser().getId(), 35);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/allfeeds")
    public String allfeeds(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        init(viewerAccount, model);

        //to do
        List<Activity> activities = activityService.getActivities(35);
        model.put("activities", activities);

        return "dashboard";
    }

    /**
     * Get viewer's friends.
     * In this case, viewer is the same as owner.
     */
    @RequestMapping(value="/friends")
    public String getFriends(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        init(viewerAccount, model);

        List<User> friends = userService.getFriends(viewerAccount.getUser().getId());
        model.put("friends", friends);

        return "dashboard";
    }

    @RequestMapping(value="/invitations", method=GET)
    public String getInvitations(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        init(viewerAccount, model);
        return "dashboard";
    }

    @RequestMapping(value="/invitations/{invitationId}/_accept", method=POST)
    public HttpEntity<?> accept(@PathVariable("invitationId") String invitationId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Invitation invitation = invitationService.getInvitation("#"+invitationId);
        invitationService.acceptInvitation(invitation);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value="/invitations/{invitationId}/_decline", method=POST)
    public HttpEntity<?> decline(@PathVariable("invitationId") String invitationId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Invitation invitation = invitationService.getInvitation("#"+invitationId);

        invitationService.declineInvitation(invitation);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/_updateEmail", method=POST)
    public HttpEntity<?> updateEmail(@Valid EmailForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        form.setAccount(viewerAccount);
        try {
            userService.updateEmail(form);
        }
        catch(Exception exc) {
            logger.warn(exc.getMessage());
            throw exc;
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/_updateProfile", method=POST)
    public String updateProfile(@Valid ProfileForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        form.setAccount(viewerAccount);
        userService.updateProfile(form);

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/_updatePassword", method=POST)
    public HttpEntity<?> updatePassword(@Valid PasswordForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        
        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(!form.getPassword().equals(form.getConfirm())) {
            logger.warn("confirm mis match");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        form.setAccount(viewerAccount);
        userService.updatePassword(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
