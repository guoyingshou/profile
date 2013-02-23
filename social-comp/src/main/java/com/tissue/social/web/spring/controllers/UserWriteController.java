package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Impression;
import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.controllers.AccessController;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
public class UserWriteController extends AccessController {

    @Autowired
    private UserService userService;

    /**
     * send invitation.
     */
    @RequestMapping(value="/users/{id}/invites/_create", method=POST)
    public HttpEntity<?> invite(@PathVariable("id") String id, @RequestParam("content") String content, Map model) {

        id = "#" + id;
        userService.inviteFriend(SecurityUtil.getViewerAccountId(), id, content);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add/Update resume.
     */
    @RequestMapping(value="/users/{userId}/resume/_create", method=POST)
    public HttpEntity<?> updateResume(@PathVariable("userId") String userId, @RequestParam("content") String content, Map model) throws Exception {

        userService.addResume("#" + userId, content);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add impression.
     */
    @RequestMapping(value="/users/{userId}/impressions/_create", method=POST)
    public String addImpression(@PathVariable("userId") String userId, @RequestParam("content") String content, Map model) throws Exception {

        Impression impression = new Impression();
        
        User from = new User();
        from.setId(SecurityUtil.getViewerAccountId());
        //from.setDisplayName(SecurityUtil.getDisplayName());
        impression.setFrom(from);

        User to = new User();
        to.setId(userId);
        impression.setTo(to);

        impression.setContent(content);

        userService.addImpression(impression);

        model.put("impression", impression);

        return "fragments/newImpression";
    }

    @RequestMapping(value="/invitations/{id}/_accept", method=POST)
    public HttpEntity<?> accept(@PathVariable("id") String id, Map model) {
        userService.acceptInvitation("#"+id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value="/invitations/{id}/_decline", method=POST)
    public HttpEntity<?> decline(@PathVariable("id") String id, Map model) {
        userService.declineInvitation("#"+id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value="/about/_create", method=POST)
    public String addAbout(@RequestParam("content") String content, Map model) throws Exception {

        /**
        About about = new About();
        
        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        about.setUser(user);

        about.setContent(content);

        about = aboutService.addAbout(about);

        model.put("about", about);
        */

        return "fragments/newAbout";
    }

}
