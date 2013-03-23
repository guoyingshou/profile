package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.social.Invitation;
import com.tissue.plan.Plan;
import com.tissue.social.web.model.InvitationForm;
import com.tissue.social.services.InvitationService;
import com.tissue.social.services.ViewerService;

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

    @RequestMapping(value="/invitations", method=GET)
    public String getInvitations(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "invitations");

        List<Plan> plans = viewerService.getPlans(viewerAccount);
        model.put("plans", plans);

        List<Invitation> invitations = invitationService.getInvitationsReceived(viewerAccount);
        model.put("invitationsReceived", invitations);
 
        return "invitations";
    }

    /**
     * send invitation.
     */
    @RequestMapping(value="/invitations/_create", method=POST)
    public HttpEntity<?> createInvitation(@Valid InvitationForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            logger.warn(result.getAllErrors().toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        form.setAccount(viewerAccount);
        invitationService.createInvitation(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/invitations/{invitationId}/_accept", method=GET)
    //public HttpEntity<?> accept(@PathVariable("invitationId") Invitation invitation, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
    public String accept(@PathVariable("invitationId") Invitation invitation, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        invitationService.acceptInvitation(invitation);
        return "redirect:/invitations";
        //return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value="/invitations/{invitationId}/_decline", method=GET)
    public String decline(@PathVariable("invitationId") Invitation invitation, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        invitationService.declineInvitation(invitation);
        return "redirect:/invitations";
        //return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}