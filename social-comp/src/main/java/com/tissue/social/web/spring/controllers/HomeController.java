package com.tissue.social.web.spring.controllers;

import com.tissue.core.User;
import com.tissue.core.Account;
import com.tissue.core.Verification;
import com.tissue.commons.services.AccountService;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.social.Activity;
import com.tissue.social.web.model.SignupForm;
import com.tissue.social.web.model.VerificationForm;
import com.tissue.social.services.ActivityService;
import com.tissue.social.services.VerificationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value="/home")
    public String index(@RequestParam(value="s", required=false) String s, Map model) {

        if(SecurityUtil.getViewerAccountId() != null) {
            model.clear();
            return "redirect:/dashboard";
        }

        if(s != null) {
            model.put("signout", true);
        }

        List<Activity> activities = activityService.getActivities(16);
        model.put("activities", activities);

        return "home";
    }

    @RequestMapping(value="/signup", method=GET)
    public String signupForm(Map model) {
        model.put("signupForm", new SignupForm());
        return "signup";
    }

    /**
     * Signup.
     */
    @RequestMapping(value="/signup", method=POST)
    public String signup(@Valid @ModelAttribute("signupForm") SignupForm form, BindingResult result, Locale locale, Map model) {

        String accountId = null;
        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return "signup";
        }
        if(!form.getConfirm().equals(form.getPassword())) {
            logger.warn("confirm mis match");

            result.rejectValue("confirm", "Mismatch.confirm", "confirm mismatch");
            return "signup";
        }

        try {
            accountId = accountService.addUser(form);
        }
        catch(Exception exc) {
            exc.printStackTrace();
            logger.warn(exc.getMessage());

            if(exc.getMessage().contains("Account.username")) {
                result.rejectValue("username", "Taken.username", "username is already taken");
            }
            if(exc.getMessage().contains("Account.email")) {
                result.rejectValue("email", "Taken.email", "email is already taken");
            }
            return "signup";
        }

        VerificationForm verificationForm = new VerificationForm();
        verificationForm.setCode(UUID.randomUUID().toString());
        verificationForm.setEmail(form.getEmail());

        Account account = form.getAccount();
        account.setId(accountId);
        verificationForm.setAccount(account);

        verificationService.sendVerificationEmail(verificationForm, locale);

        model.clear();
        return "redirect:/signin?t=n";
    }

    @RequestMapping(value="/verifications/{code}")
    public String verifyCode(@PathVariable("code") Verification verification, Map model) {

        accountService.setVerified(verification.getAccount().getId());
        verificationService.deleteVerification(verification.getId());

        return "verificationSuccess";
    }

    /**
    @RequestMapping(value="/checkUsername", method=POST)
    public HttpEntity<?> checkUsername(@RequestParam(value="username") String username, Map model) {

        boolean usernameValid = ((username == null) || "".equals(username.trim())) ? false : true;
        boolean exist = usernameValid && accountService.isUsernameExist(username);
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

        boolean exist = !emailValid || accountService.isEmailExist(email);
        if(exist) {
             return new ResponseEntity(HttpStatus.CONFLICT);
        }
        else {
            return HttpEntity.EMPTY;
        }
    }
    */
}
