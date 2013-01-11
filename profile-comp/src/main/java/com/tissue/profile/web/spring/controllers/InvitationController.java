package com.tissue.profile.web.spring.controllers;

import com.tissue.core.profile.User;
import com.tissue.core.profile.Invitation;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.profile.service.InvitationService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;


@Controller
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    /**
     * Get all unprocessed invitation.
     */
    @RequestMapping(value="/invitations")
    public String getInvitations(Map model) {

        String viewerId = SecurityUtil.getViewerId();

        if(viewerId != null) {
            List<Invitation> invitations = invitationService.getInvitations(viewerId);
            model.put("invitations", invitations);
        }

        model.put("viewer", SecurityUtil.getViewer());
        return "invitationList";
    }

    /**
     * Process invitation.
     */
    @RequestMapping(value="/invitations/{id}", method=POST)
    @ResponseBody
    public String processInvitation(@PathVariable("id") String id, @RequestParam("action") String action, Map model) {

        //String viewerId = SecurityUtil.getUserId();
        //model.put("viewer", SecurityUtil.getUser());

        if("decline".equals(action)) {
            invitationService.declineInvitation(id);
        }
        if("accept".equals(action)) {
            invitationService.acceptInvitation(id);
        }

        return "id: " + id + ", action: " + action;
    }
}
