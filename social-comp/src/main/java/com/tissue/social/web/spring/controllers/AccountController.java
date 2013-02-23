package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.services.UserService;
import com.tissue.social.web.model.AccountForm;
//import com.tissue.social.web.model.ProfileForm;
//import com.tissue.social.web.model.EmailForm;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        userService.addUser(form);

        /**
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("guoyingshou@yahoo.com.cn");
        msg.setSubject("welcome back");
        msg.setText(form.getUsername() + ":" + form.getDisplayName() + ":" + form.getEmail());
        mailSender.send(msg);
        */

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/_updateEmail", method=POST)
    public String updateEmail(@Valid AccountForm form, BindingResult result, Map model) {

        /**
        if(result.hasErrors()) {
            throw new InvalidParameterException("content invalid");
        }

        String viewerId = SecurityUtil.getViewerId();
        */

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/_updateProfile", method=POST)
    public String updateProfile(@Valid AccountForm form, BindingResult result, Map model) {

        String viewerAccountId = SecurityUtil.getViewerAccountId();

        if(result.hasErrors()) {
            throw new InvalidParameterException("content invalid");
        }

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/_updatePassword", method=POST)
    public String changePass(AccountForm form, Map model) {
        String viewerAccountId = SecurityUtil.getViewerAccountId();
        
        if(!form.getPassword().equals(form.getConfirm())) {
            throw new InvalidParameterException("confirm mismatch");
        }

        /**
        User user = new User();
        user.setId(viewerId);
        */

        return "redirect:/dashboard";
    }

    @RequestMapping(value="/preAddUsername", method=POST)
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

    @RequestMapping(value="/preAddEmail", method=POST)
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

    @RequestMapping(value="/preUpdateEmail", method=POST)
    public HttpEntity<?> checkEmailOwned(@RequestParam(value="email") String email, Map model) {

        String viewerAccountId = SecurityUtil.getViewerAccountId();
        boolean emailValid = ((email == null) || "".equals(email.trim()) || !email.contains("@")) ? false : true;
        boolean exist = emailValid && userService.isEmailExist(viewerAccountId, email);
        if(exist) {
             return new ResponseEntity(HttpStatus.CONFLICT);
        }
        else {
            return HttpEntity.EMPTY;
        }
    }

}
