package com.tissue.social.web.spring.controllers;

import com.tissue.core.User;
import com.tissue.core.Account;
import com.tissue.core.social.Activity;
import com.tissue.commons.services.AccountService;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.social.web.model.SignupForm;
import com.tissue.social.web.model.VerificationForm;
import com.tissue.social.web.model.ResetRequestForm;
import com.tissue.social.web.model.ResetPasswordForm;
import com.tissue.social.services.ActivityService;

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
    private ActivityService activityService;

    @RequestMapping(value="/home")
    public String index(Map model) {

        if(SecurityUtil.getViewerAccountId() != null) {
            return "redirect:dashboard";
        }

        List<Activity> activities = activityService.getActivitiesForNewUser(35);
        model.put("activities", activities);

        return "home";
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

    @RequestMapping(value="/signup", method=GET)
    public String signupForm(Map model) {
        SignupForm signupForm = new SignupForm();
        model.put("signupForm", signupForm);
        return "signup";
    }

    /**
     * Signup.
     */
    @RequestMapping(value="/signup", method=POST)
    public String signup(@Valid @ModelAttribute("signupForm") SignupForm form, BindingResult result, Locale locale) {

        String accountId = null;
        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return "signup";
        }
        if(!form.getConfirm().equals(form.getPassword())) {
            logger.warn("confirm mis match");

            result.rejectValue("confirm", "Mismatch.signupForm.confirm", "confirm mismatch");
            return "signup";
        }

        try {
            accountId = accountService.addUser(form);
        }
        catch(Exception exc) {
            logger.warn(exc.getMessage());

            if(exc.getMessage().contains("Account.username")) {
                result.rejectValue("username", "Taken.signupForm.username", "username is already taken");
            }
            if(exc.getMessage().contains("Account.email")) {
                result.rejectValue("email", "Taken.signupForm.email", "email is already taken");
            }
            return "signup";
        }

        VerificationForm verificationForm = new VerificationForm();
        verificationForm.setCode(UUID.randomUUID().toString());
        verificationForm.setEmail(form.getEmail());

        Account viewerAccount = new Account();
        viewerAccount.setId(accountId);
        verificationForm.setAccount(viewerAccount);

        accountService.sendVerificationEmail(verificationForm, locale);

        return "redirect:/dashboard";
    }

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

    @RequestMapping(value="/login")
    public String loginForm(@RequestParam(value="error", required=false) String error, Map model) {
        model.put("error", error);
        return "login";
    }

    @RequestMapping(value="/verifications/{code}")
    public String verifyCode(@PathVariable("code") String code, Map model) {
        String accountId = accountService.getAccountId(code);
        if(accountId == null) {
            return "verificationFail";
        }

        accountService.setVerified(accountId);

        return "verificationSuccess";
    }

    @RequestMapping(value="/resetRequest", method=GET)
    public String requestResetPassword(Map model) {
        model.put("resetRequestForm", new ResetRequestForm());
        return "resetRequestForm";
    }

    @RequestMapping(value="/resetRequest", method=POST)
    public String processReset(@Valid ResetRequestForm form, BindingResult result, Locale locale) {
        if(result.hasErrors()) {
            return "resetRequestForm";
        }

        boolean exist = accountService.isEmailExist(form.getEmail());
        if(!exist) {
            result.rejectValue("email", "NonExist.resetRequestForm.email", "Email not exist");
            return "resetRequestForm";
        }

        form.setCode(UUID.randomUUID().toString());
        accountService.sendResetEmail(form, locale);
        return "redirect:/resetRequestSuccess";
    }

    @RequestMapping(value="/resetRequestSuccess", method=GET)
    public String resetRequestSuccess() {
        return "resetRequestSuccess";
    }

    @RequestMapping(value="/reset/{code}")
    public String resetPasswordForm(@PathVariable("code") String code, Map model) {

        boolean exist = accountService.isCodeExist(code);
        if(!exist) {
            return "invalidResetCode";
        }

        ResetPasswordForm form = new ResetPasswordForm();
        form.setCode(code);
        model.put("resetPasswordForm", form);
        return "resetPasswordForm";
    }

    @RequestMapping(value="/reset", method=POST)
    public String processReset(@Valid ResetPasswordForm form, BindingResult result, Locale locale) {

        if(result.hasErrors()) {
            return "resetPasswordForm";
        }
        if(!form.getPassword().equals(form.getConfirm())) {
            result.rejectValue("confirm", "Mismatch.resetPasswordForm.confirm", "confirm mismatch");
            return "resetPasswordForm";
        }

        boolean exist = accountService.isCodeExist(form.getCode());
        if(!exist) {
            return "invalidResetCode";
        }

        accountService.updatePassword(form);

        return "redirect:/resetSuccess";
    }

    @RequestMapping(value="/resetSuccess", method=GET)
    public String resetSuccess() {
        return "resetSuccess";
    }


}
