package com.tissue.social.web.spring.controllers.ajax;

import com.tissue.core.social.About;
import com.tissue.core.social.User;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.service.AboutService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Map;

@Controller
public class AboutAjaxController {

    @Autowired
    private AboutService aboutService;

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
