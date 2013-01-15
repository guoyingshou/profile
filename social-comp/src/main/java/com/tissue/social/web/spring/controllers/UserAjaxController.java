package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Impression;
import com.tissue.core.social.User;
import com.tissue.core.social.About;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.service.UserService;
import com.tissue.commons.social.service.AboutService;
import com.tissue.social.service.InvitationService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;

@Controller
public class UserAjaxController {

    @Autowired
    private UserService userService;

    @Autowired
    private AboutService aboutService;

    @Autowired
    private InvitationService invitationService;

    @RequestMapping(value="/users/{id}/invites", method=POST)
    @ResponseBody
    public String invite(@PathVariable("id") String id, @RequestParam("content") String content, Map model) {

        invitationService.inviteFriend(SecurityUtil.getViewerId(), id, content);

        return "ok";
    }

    /**
     * Add/Update resume.
     */
    @RequestMapping(value="/users/{userId}/resume", method=POST)
    @ResponseBody
    public String updateResume(@PathVariable("userId") String userId, @RequestParam("content") String content, Map model) throws Exception {

        userService.addResume(userId, content);
        return "ok";
    }

    /**
     * Add impression.
     */
    @RequestMapping(value="/users/{userId}/impressions", method=POST)
    public String addImpression(@PathVariable("userId") String userId, @RequestParam("content") String content, Map model) throws Exception {

        Impression impression = new Impression();
        
        User from = new User();
        from.setId(SecurityUtil.getViewerId());
        from.setDisplayName(SecurityUtil.getDisplayName());
        impression.setFrom(from);

        User to = new User();
        to.setId(userId);
        impression.setTo(to);

        impression.setContent(content);

        userService.addImpression(impression);

        model.put("impression", impression);

        return "fragments/newImpression";
    }

    /**
     * Process invitation.
     */
    @RequestMapping(value="/invitations/{id}", method=POST)
    @ResponseBody
    public String processInvitation(@PathVariable("id") String id, @RequestParam("action") String action, Map model) {

        if("decline".equals(action)) {
            invitationService.declineInvitation(id);
        }
        if("accept".equals(action)) {
            invitationService.acceptInvitation(id);
        }
        return "id: " + id + ", action: " + action;
    }

    /**
     * Add about.
     */
    @RequestMapping(value="/about", method=POST)
    public String addAbout(@RequestParam("content") String content, Map model) throws Exception {

        About about = new About();
        
        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        about.setUser(user);

        about.setContent(content);

        about = aboutService.addAbout(about);

        model.put("about", about);

        return "fragments/newAbout";
    }


}
