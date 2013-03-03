package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.services.UserService;
import com.tissue.social.web.model.UserForm;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.BindStatus;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup", method=GET)
    public String signupForm(Map model) {
        UserForm user = new UserForm();
        model.put("user", user);
        return "signup";
    }

    /**
     * Signup.
     */
    @RequestMapping(value="/signup", method=POST)
    public String signup(@Valid @ModelAttribute("user") UserForm form, BindingResult result) {

        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return "signup";
        }
        if(!form.getConfirm().equals(form.getPassword())) {
            result.rejectValue("confirm", "Mismatch.confirm", "confirm mismatch");
            logger.warn(result.getAllErrors().toString());
            return "signup";
        }

        try {
            userService.addUser(form);
        }
        catch(Exception exc) {
            if(exc.getMessage().contains("account.username")) {
                result.rejectValue("username", "Taken.username", "username is already taken");
            }
            if(exc.getMessage().contains("account.email")) {
                result.rejectValue("email", "Taken.email", "email is already taken");
            }
            logger.warn(result.getAllErrors().toString());
            return "signup";
        }

        /**
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("guoyingshou@yahoo.com.cn");
        msg.setSubject("welcome back");
        msg.setText(form.getUsername() + ":" + form.getDisplayName() + ":" + form.getEmail());
        mailSender.send(msg);
        */

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/checkUsername", method=POST)
    public HttpEntity<?> checkUsername(@RequestParam(value="username") String username, Map model) {

        boolean usernameValid = ((username == null) || "".equals(username.trim())) ? false : true;
        boolean exist = usernameValid && userService.isUsernameExist(username);
        if(exist) {
             return new ResponseEntity(HttpStatus.CONFLICT);
        }
        else {
            return HttpEntity.EMPTY;
        }
    }

    @RequestMapping(value="/checkEmail", method=POST)
    public HttpEntity<?> checkEmail(@RequestParam(value="email") String email, Map model) {

        boolean emailValid = ((email == null) || "".equals(email.trim()) || !email.contains("@")) ? false : true;

        boolean exist = !emailValid || userService.isEmailExist(email);
        if(exist) {
             return new ResponseEntity(HttpStatus.CONFLICT);
        }
        else {
            return HttpEntity.EMPTY;
        }
    }

    @RequestMapping(value="/login")
    public String loginForm(@RequestParam(value="error", required=false) String error, Map model) {
        model.put("error", error);
        return "login";
    }
}
