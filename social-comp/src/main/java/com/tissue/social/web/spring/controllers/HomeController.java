package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.social.Activity;
import com.tissue.core.plan.Plan;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.social.service.UserService;
import com.tissue.commons.social.service.ActivityService;
//import com.tissue.social.web.model.UserForm;
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
public class HomeController extends ViewerSetter {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value="/login")
    public String loginForm(Map model) {
        return "login";
    }

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

    /**
     * Signup.
     */
    @RequestMapping(value="/signup", method=POST)
    public String signup(@Valid @ModelAttribute("account") AccountForm form, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println(error);
            }
            return "signup";
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setDisplayName(form.getDisplayName());
        user.setHeadline(form.getHeadline());
        user.setEmail(form.getEmail());

        String md5 = Hashing.md5().hashString(form.getPassword(), Charset.forName("utf-8")).toString();
        user.setPassword(md5);

        user.setCreateTime(new Date());

        userService.addUser(user);
        return "redirect:/dashboard";
    }

    @RequestMapping(value="/users/{userId}", method=POST)
    public String updateUser(@PathVariable("userId") String userId, AccountForm form, Map model) {
        User user = new User();
        user.setId(userId);
        user.setDisplayName(form.getDisplayName());
        user.setHeadline(form.getHeadline());
        user.setEmail(form.getEmail());
        userService.updateUser(user);
        return "redirect:/dashboard";
    }

    @RequestMapping(value="/users/{userId}/pass", method=POST)
    public String changePass(@PathVariable("userId") String userId, AccountForm form, Map model) {
        User user = new User();
        user.setId(userId);

        String md5 = Hashing.md5().hashString(form.getPassword(), Charset.forName("utf-8")).toString();
        user.setPassword(md5);

        userService.changePassword(user);

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/home")
    public String index(Map model) {
        List<Activity> activities = activityService.getActivitiesForNewUser(15);
        model.put("activities", activities);
        return "home";
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, @ModelAttribute("viewer") User viewer) {

        model.put("owner", viewer);

        if(viewer.getFriends().size() == 0) {
            List<Activity> activities = activityService.getActivities(25);
            model.put("activities", activities);
        }
        else {
            List<Activity> activities = activityService.getFriendsActivities(viewer.getId(), 15);
            model.put("activities", activities);
        }

        return "dashboard";
    }

    @RequestMapping(value="/watchedfeeds")
    public String watchedfeeds(Map model, @ModelAttribute("viewer") User viewer) {

        model.put("owner", viewer);

        List<Activity> activities = activityService.getFriendsActivities(viewer.getId(), 15);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/allfeeds")
    public String allfeeds(Map model, @ModelAttribute("viewer") User viewer) {

        model.put("owner", viewer);

        List<Activity> activities = activityService.getActivities(25);
        model.put("activities", activities);

        return "dashboard";
    }

    @RequestMapping(value="/invitations", method=GET)
    public String getInvitations(Map model, @ModelAttribute("viewer") User viewer) {
        model.put("owner", viewer);
        return "invitations";
    }

}
