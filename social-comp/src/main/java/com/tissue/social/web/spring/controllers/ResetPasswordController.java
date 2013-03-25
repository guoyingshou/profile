package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.Reset;
import com.tissue.social.web.model.ResetRequestForm;
import com.tissue.social.web.model.PasswordForm;
import com.tissue.social.services.ResetService;
import com.tissue.social.services.AccountService;

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
public class ResetPasswordController {

    private static Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    private ResetService resetService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/resetRequest", method=GET)
    public String requestResetPassword(Map model) {
        model.put("resetRequestForm", new ResetRequestForm());
        return "createResetRequestFormView";
    }

    @RequestMapping(value="/resetRequest", method=POST)
    public String processReset(@Valid ResetRequestForm form, BindingResult result, Locale locale) {

        if(result.hasErrors()) {
            return "createResetRequestFormView";
        }

        Account account = accountService.getAccountByEmail(form.getEmail());
        if(account == null) {
            result.rejectValue("email", "NonExist.resetRequestForm.email", "Email not exist");
            return "createResetRequestFormView";
        }
        form.setAccount(account);
        form.setCode(UUID.randomUUID().toString());

        resetService.sendResetEmail(form, locale);
        return "redirect:/createResetRequestSuccess";
    }

    @RequestMapping(value="/resetRequestSuccess", method=GET)
    public String resetRequestSuccess() {
        return "createResetRequestSuccess";
    }

    @RequestMapping(value="/reset/{code}")
    public String resetPasswordForm(@PathVariable("code") Reset reset, Map model) {

        if(reset == null) {
            throw new IllegalArgumentException("no such code");
        }

        model.put("code", reset.getCode());
        model.put("passwordForm", new PasswordForm());
        return "createResetPasswordFormView";
    }

    @RequestMapping(value="/reset/{code}", method=POST)
    public String processReset(@PathVariable("code") Reset reset, @Valid PasswordForm form, BindingResult result, Locale locale) {

        if(reset == null) {
            throw new IllegalArgumentException("no such code");
        }

        if(result.hasErrors()) {
            return "createResetPasswordFormView";
        }

        if(!form.getPassword().equals(form.getConfirm())) {
            result.rejectValue("confirm", "Mismatch.resetPasswordForm.confirm", "confirm mismatch");
            return "createResetPasswordFormView";
        }

        form.setAccount(reset.getAccount());
        accountService.updatePassword(form);

        resetService.deleteReset(reset.getId());

        return "redirect:/resetPasswordSuccess";
    }

    @RequestMapping(value="/resetPasswordSuccess", method=GET)
    public String resetSuccess() {
        return "createResetPasswordSuccess";
    }

}
