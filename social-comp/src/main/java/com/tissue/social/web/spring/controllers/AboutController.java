package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.About;
import com.tissue.core.social.User;
import com.tissue.commons.social.services.AboutService;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.security.util.SecurityUtil;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import java.util.List;
import java.util.Map;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AboutController {

    @Autowired
    protected UserService userService;

    @Autowired
    private AboutService aboutService;

    @ModelAttribute("viewer")
    public User setupViewer(Map model) {
        String viewerAccountId = SecurityUtil.getViewerAccountId();
        if(viewerAccountId == null) {
            return null;    
        }
        return userService.getUserByAccount(viewerAccountId);
    }

    @RequestMapping(value="/about", method=GET)
    public String about(Map model) {
        List<About> abouts = aboutService.getAbouts();
        model.put("abouts", abouts);
        return "about";
    }

    @RequestMapping("/te2")
    public String test1(HttpServletRequest req) {
        ServletContext ctx = req.getServletContext();

        Enumeration enu = ctx.getAttributeNames();
        while(enu.hasMoreElements()) {
            Object obj = ctx.getAttribute(enu.nextElement().toString());
            System.out.println("\nname: " + enu.nextElement());
            System.out.println("obj: " + obj);
            System.out.println("obj type: " + obj.getClass());
        }

        return "test1";
    }
    
}
