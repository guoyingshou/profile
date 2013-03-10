package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.social.Account;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.social.web.model.SignupForm;
import com.tissue.social.web.model.VerificationForm;
import com.tissue.social.services.UserService;
import com.tissue.social.services.VerificationService;

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
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

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
            accountId = userService.addUser(form);
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

        verificationService.sendVerificationEmail(verificationForm, locale);

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

    @RequestMapping(value="/verifications/{code}")
    public String verifyCode(@PathVariable("code") String code, Map model) {
        String accountId = verificationService.getAccountId(code);
        if(accountId == null) {
            return "verificationFail";
        }

        verificationService.setVerified(accountId);

        return "verificationSuccess";
    }
}
