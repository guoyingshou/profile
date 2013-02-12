package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.service.UserService;
import com.tissue.social.web.model.AccountForm;
import com.tissue.social.web.model.ProfileForm;
import com.tissue.social.web.model.EmailForm;

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
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

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

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.security.InvalidParameterException;

@Controller
public class AccountController {
//    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserService userService;

    @ModelAttribute("locale")
    public String initLocale(Locale locale) {
        return locale.toString();
    }

    @RequestMapping(value="/login")
    public String loginForm(@RequestParam(value="error", required=false) String error, Map model) {
        model.put("error", error);
        return "login";
    }

    @RequestMapping(value="/signup", method=GET)
    public String signupForm(Map model) {
        return "signup";
    }

    /**
     * Signup.
     */
    @RequestMapping(value="/signup", method=POST)
    public String signup(@Valid @ModelAttribute("account") AccountForm form, BindingResult result) {
        if(result.hasErrors()) {
            throw new InvalidParameterException("content invalid");
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

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("guoyingshou@yahoo.com.cn");
        msg.setSubject("welcome back");
        msg.setText(form.getUsername() + ":" + form.getDisplayName() + ":" + form.getEmail());
        mailSender.send(msg);

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/changeContact", method=POST)
    public String updateUser(@Valid EmailForm form, BindingResult result, Map model) {

        if(result.hasErrors()) {
            throw new InvalidParameterException("content invalid");
        }

        String viewerId = SecurityUtil.getViewerId();

        User user = new User();
        user.setId(viewerId);
        user.setEmail(form.getEmail());
        userService.updateEmail(user);
        return "redirect:/dashboard";
    }

    @RequestMapping(value="/changeProfile", method=POST)
    public String updateUser(@Valid ProfileForm form, BindingResult result, Map model) {

        String viewerId = SecurityUtil.getViewerId();

        if(result.hasErrors()) {
            throw new InvalidParameterException("content invalid");
        }

        User user = new User();
        user.setId(viewerId);
        user.setDisplayName(form.getDisplayName());
        user.setHeadline(form.getHeadline());
        userService.updateUser(user);
        return "redirect:/dashboard";
    }

    @RequestMapping(value="/changePass", method=POST)
    public String changePass(AccountForm form, Map model) {
        String viewerId = SecurityUtil.getViewerId();
        
        if(!form.getPassword().equals(form.getConfirm())) {
            throw new InvalidParameterException("confirm mismatch");
        }

        User user = new User();
        user.setId(viewerId);

        String md5 = Hashing.md5().hashString(form.getPassword(), Charset.forName("utf-8")).toString();
        user.setPassword(md5);

        userService.changePassword(user);

        return "redirect:/dashboard";
    }
}
