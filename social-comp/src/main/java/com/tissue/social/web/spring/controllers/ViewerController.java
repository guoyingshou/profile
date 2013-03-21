package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.social.Invitation;
import com.tissue.social.Impression;
import com.tissue.social.Activity;
import com.tissue.plan.Plan;
import com.tissue.plan.Topic;
import com.tissue.social.web.model.UpdateProfileForm;
import com.tissue.social.web.model.EmailForm;
import com.tissue.social.web.model.PasswordForm;
//import com.tissue.social.web.model.InvitationForm;
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

        List<Plan> plans = viewerService.getPlans(viewerAccount);
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

        List<Plan> plans = viewerService.getPlans(viewerAccount);
        model.put("plans", plans);

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount);
        model.put("invitationsReceived", invitations);
 
        List<Activity> activities = viewerService.getActivities(35);
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

        List<Plan> plans = viewerService.getPlans(viewerAccount);
        model.put("plans", plans);

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount);
        model.put("invitationsReceived", invitations);
 
        List<User> friends = viewerService.getFriends(viewerAccount.getUser().getId());
        model.put("friends", friends);

        model.put("selected", "friends");
        return "friends";
    }

    @RequestMapping(value="/_updateProfile", method=POST)
    public String updateProfile(@Valid UpdateProfileForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        form.setAccount(viewerAccount);
        viewerService.updateProfile(form);

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/_updateEmail", method=POST)
    public HttpEntity<?> updateEmail(@Valid EmailForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        form.setAccount(viewerAccount);
        try {
            accountService.updateEmail(form);
        }
        catch(Exception exc) {
            logger.warn(exc.getMessage());
            throw exc;
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
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
        accountService.updatePassword(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add impression.
     */
    @RequestMapping(value="/impressions/_create", method=POST)
    public String addImpression(@Valid ImpressionForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        form.setAccount(viewerAccount);
        viewerService.addImpression(form);

        return "redirect:/users/" + form.getTo().getId().replace("#","") + "/impressions";
    }

}
