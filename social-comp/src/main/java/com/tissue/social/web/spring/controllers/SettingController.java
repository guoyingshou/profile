package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.commons.services.AccountService;
import com.tissue.commons.services.ViewerService;
import com.tissue.social.web.model.HeadlineForm;
import com.tissue.social.web.model.EmailForm;
import com.tissue.social.web.model.PasswordForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.security.AccessControlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SettingController {

    private static Logger logger = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/_setting")
    public String setting(Map model) {
        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "profile");
        return "setting";
    }

    @RequestMapping(value="/_updateHeadline")
    public String updateHeadlineForm(Map model) {
        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "headline");
        model.put("headlineForm", viewerAccount.getUser());
        return "updateHeadlineFormView";
    }

    @RequestMapping(value="/_updateHeadline", method=POST)
    public String updateHeadline(@Valid HeadlineForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil: " + viewerAccount);
        }
        
        model.put("viewerAccount", viewerAccount);

        if(result.hasErrors()) {
            model.put("selected", "headline");
            return "updateHeadlineFormView";
        }

        form.setId(viewerAccount.getUser().getId());
        viewerService.updateHeadline(form);

        return "redirect:/_setting";
    }

    @RequestMapping(value="/_updateEmail")
    public String updateEmailForm(Map model) {

        Account viewerAccount = viewerService.getViewerAccount();

        model.put("viewerAccount", viewerAccount);
        model.put("selected", "email");
        model.put("emailForm", viewerAccount);

        return "updateEmailFormView";
    }

    @RequestMapping(value="/_updateEmail", method=POST)
    public String updateEmail(@Valid EmailForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil: " + viewerAccount);
        }

        model.put("viewerAccount", viewerAccount);

        if(result.hasErrors()) {
            model.put("selected", "email");
            return "updateEmailFormView";
        }

        try {
            form.setAccount(viewerAccount);
            accountService.updateEmail(form);
        }
        catch(Exception exc) {
            if(exc.getMessage().contains("Account.email")) {
                result.rejectValue("email", "Taken.passwordForm.email", "email is already taken");
            }
            model.put("selected", "email");
            return "updateEmailFormView";
        }

        return "redirect:/_setting";
    }

    @RequestMapping(value="/_updatePassword")
    public String updatePasswordForm(Map model) {
        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "password");
        model.put("passwordForm", new PasswordForm());
        return "updatePasswordFormView";
    }

    @RequestMapping(value="/_updatePassword", method=POST)
    public String updatePassword(@Valid PasswordForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil: " + viewerAccount);
        }

        model.put("viewerAccount", viewerAccount);
       
        if(result.hasErrors()) {
            model.put("selected", "password");
            return "updatePasswordFormView";
        }

        if(!form.getPassword().equals(form.getConfirm())) {
            model.put("selected", "password");
            result.rejectValue("confirm", "Mismatch.passwordForm.confirm", "confirm mismatch");
            return "updatePasswordFormView";
        }

        form.setAccount(viewerAccount);
        accountService.updatePassword(form);

        return "redirect:/_setting";
    }
}
