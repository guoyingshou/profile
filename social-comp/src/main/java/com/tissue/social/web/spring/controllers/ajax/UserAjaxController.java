package com.tissue.social.web.spring.controllers.ajax;

import com.tissue.core.social.Impression;
import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.service.UserService;

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
public class UserAjaxController {

    @Autowired
    private UserService userService;

    /**
     * send invitation.
     */
    @RequestMapping(value="/users/{id}/invites", method=POST)
    @ResponseBody
    public String invite(@PathVariable("id") String id, @RequestParam("content") String content, Map model) {

        userService.inviteFriend(SecurityUtil.getViewerId(), id, content);

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
    @RequestMapping(value="/invitations/{id}", method=POST)
    @ResponseBody
    public String processInvitation(@PathVariable("id") String id, @RequestParam("action") String action, Map model) {

        if("decline".equals(action)) {
            userService.declineInvitation(id);
        }
        if("accept".equals(action)) {
            userService.acceptInvitation(id);
        }
        return "id: " + id + ", action: " + action;
    }
     */

    @RequestMapping(value="/invitations/{id}/accept", method=POST)
    public HttpEntity<?> accept(@PathVariable("id") String id, Map model) {
        userService.acceptInvitation(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    @RequestMapping(value="/invitations/{id}/decline", method=POST)
    public HttpEntity<?> decline(@PathVariable("id") String id, Map model) {
        userService.declineInvitation(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
}
