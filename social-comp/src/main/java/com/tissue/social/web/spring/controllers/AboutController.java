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

}
