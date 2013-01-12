package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Impression;
import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.social.service.UserService;

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


}
