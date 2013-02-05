package com.tissue.social.web.spring.controllers;

import com.tissue.core.social.About;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.social.service.AboutService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import java.util.List;
import java.util.Map;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AboutController extends ViewerSetter {

    @Autowired
    private AboutService aboutService;

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
