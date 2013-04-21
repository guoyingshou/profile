package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.Reset;
import com.tissue.commons.services.AccountService;
import com.tissue.social.web.model.ResetRequestForm;
import com.tissue.social.web.model.ResetPasswordForm;
import com.tissue.social.services.ResetService;

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
    public String processReset(@Valid ResetRequestForm form, BindingResult result, Locale locale, Map model) {

        if(result.hasErrors()) {
            return "createResetRequestFormView";
        }

        Account account = accountService.getAccountByEmail(form.getEmail());
        if(account == null) {
            result.rejectValue("email", "NotExist.email", "Email not exist");
            return "createResetRequestFormView";
        }
        form.setAccount(account);
        form.setCode(UUID.randomUUID().toString());

        resetService.sendResetEmail(form, locale);

        model.clear();
        return "redirect:/createResetRequestSuccess";
    }

    @RequestMapping(value="/createResetRequestSuccess", method=GET)
    public String resetRequestSuccess() {
        return "createResetRequestSuccess";
    }

    @RequestMapping(value="/reset/{code}")
    public String resetPasswordForm(@PathVariable("code") Reset reset, Map model) {

        if(reset == null) {
            throw new IllegalArgumentException("no such code");
        }

        ResetPasswordForm form = new ResetPasswordForm();
        form.setReset(reset);
        model.put("resetPasswordForm", form);
        return "createResetPasswordFormView";
    }

    @RequestMapping(value="/resetPassword", method=POST)
    public String processReset(@Valid ResetPasswordForm form, BindingResult result, Locale locale, Map model) {

        if(result.hasErrors()) {
            return "createResetPasswordFormView";
        }

        if(!form.getPassword().equals(form.getConfirm())) {
            result.rejectValue("confirm", "Mismatch.confirm", "confirm mismatch");
            return "createResetPasswordFormView";
        }

        form.setAccount(form.getReset().getAccount());
        accountService.updatePassword(form);
        resetService.deleteReset(form.getReset().getId());

        model.clear();
        return "redirect:/signin?t=r";
    }

}
