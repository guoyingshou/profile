package com.tissue.social.web.spring.controllers;

import com.tissue.core.User;
import com.tissue.core.Account;
import com.tissue.commons.services.AccountService;
import com.tissue.commons.services.ViewerService;
import com.tissue.social.web.model.SignupForm;

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
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private ViewerService viewerService;

    @RequestMapping(value="/admin/a", method=GET)
    public String signupForm(Map model) {
        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        SignupForm form = new SignupForm();
        form.setStatus("h");
        model.put("signupForm", form);
        return "admin/a";
    }

    /**
     * Signup.
     */
    @RequestMapping(value="/admin/a", method=POST)
    public String signup(@Valid @ModelAttribute("signupForm") SignupForm form, BindingResult result, Locale locale, Map model) {

        System.out.println("===================");
        System.out.println(form.getStatus());
        System.out.println("===================");

        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());

            Account viewerAccount = viewerService.getViewerAccount();
            model.put("viewerAccount", viewerAccount);

            return "admin/a";
        }

        try {
            accountService.addUser(form);
        }
        catch(Exception exc) {
            logger.warn(exc.getMessage());

            if(exc.getMessage().contains("Account.username")) {
                result.rejectValue("username", "Taken.username", "username is already taken");
            }
            if(exc.getMessage().contains("Account.email")) {
                result.rejectValue("email", "Taken.email", "email is already taken");
            }
            return "a";
        }

        model.clear();
        return "redirect:/admin/a";
    }

}
