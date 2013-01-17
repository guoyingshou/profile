package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.social.About;
import com.tissue.core.social.Activity;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.social.service.UserService;
import com.tissue.commons.social.service.AboutService;
import com.tissue.commons.social.service.ActivityService;
import com.tissue.social.web.model.UserForm;
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
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private AboutService aboutService;

    @Autowired
    private ActivityService activityService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @RequestMapping(value="/about", method=GET)
    public String about(Map model, Locale locale) {
        List<About> abouts = aboutService.getAbouts();
        model.put("abouts", abouts);
        return "about";
    }

    @RequestMapping(value="/login")
    public String loginForm(Map model, Locale locale) {
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
        user.setEmail(form.getEmail());

        String md5 = Hashing.md5().hashString(form.getPassword(), Charset.forName("utf-8")).toString();
        user.setPassword(md5);

        user.setCreateTime(new Date());

        userService.addUser(user);
        return "redirect:/dashboard";
    }

}
