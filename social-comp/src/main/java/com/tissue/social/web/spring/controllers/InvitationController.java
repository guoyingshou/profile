package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.commons.services.ViewerService;
import com.tissue.social.Invitation;
import com.tissue.plan.Plan;
import com.tissue.social.web.model.InvitationForm;
import com.tissue.social.services.InvitationService;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class InvitationController {

    private static Logger logger = LoggerFactory.getLogger(InvitationController.class);

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ViewerService viewerService;

    @RequestMapping(value="/users/{userId}/invitations/_create")
    public String invitationViewForm(@PathVariable("userId") User owner, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "impressions"); 
        model.put("owner", owner); 
        Boolean invitable = viewerService.isInvitable(owner.getId(), viewerAccount);
        model.put("invitable", invitable);

        model.put("invitationForm", new InvitationForm());
        return "createInvitationFormView";

    }

    @RequestMapping(value="/users/{userId}/invitations/_create", method=POST)
    public String createInvitation(@PathVariable("userId") User owner, @Valid InvitationForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();

        if(result.hasErrors()) {
            model.put("selected", "impressions"); 
            model.put("owner", owner);
            model.put("invitable", viewerService.isInvitable(owner.getId(), viewerAccount));
            return "createInvitationFormView";
        }

        form.setTo(owner);
        form.setAccount(viewerAccount);
        invitationService.createInvitation(form);

        model.clear();
        return "redirect:/users/" + owner.getId().replace("#","") + "/impressions";
    }

    @RequestMapping(value="/invitations/{invitationId}/_accept", method=GET)
    public String accept(@PathVariable("invitationId") Invitation invitation, Map model) {

        invitationService.acceptInvitation(invitation);

        model.clear();
        return "redirect:/invitations";
    }
 
    @RequestMapping(value="/invitations/{invitationId}/_decline", method=GET)
    public String decline(@PathVariable("invitationId") Invitation invitation, Map model) {

        invitationService.declineInvitation(invitation);

        model.clear();
        return "redirect:/invitations";
    }

    @RequestMapping(value="/invitations", method=GET)
    public String getInvitations(Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "invitations");

        List<Plan> plans = viewerService.getViewerPlans();
        model.put("plans", plans);

        /**
        List<Invitation> invitations = invitationService.getViewerInvitationsReceived();
        model.put("invitations", invitations);
        */
 
        return "invitations";
    }

}
