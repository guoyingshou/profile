package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.social.Invitation;
import com.tissue.core.social.Activity;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.services.UserService;
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

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    private void init(Account viewerAccount, Map model) {
        List<Plan> plans = userService.getPlansByAccount(viewerAccount.getId());
        model.put("plans", plans);

        model.put("owner", viewerAccount.getUser());

        List<Invitation> invitations = userService.getInvitationsReceived(viewerAccount.getUser().getId());
        model.put("invitationsReceived", invitations);

    }

    @RequestMapping(value="/signout")
    public String signout(HttpSession ses, HttpServletRequest req, HttpServletResponse res, Map model) {

        model.put("viewerAccount", null);

        List<Activity> activities = userService.getActivitiesForNewUser(35);
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

        List<Activity> activities = userService.getActivitiesForNewUser(35);
        model.put("activities", activities);

        return "home";
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        init(viewerAccount, model);

        List<Activity> activities = userService.getWatchedActivities(viewerAccount.getId(), 35);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/allfeeds")
    public String allfeeds(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        init(viewerAccount, model);

        //to do
        List<Activity> activities = userService.getActivities(35);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/invitations", method=GET)
    public String getInvitations(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        init(viewerAccount, model);
        return "dashboard";
    }

}
