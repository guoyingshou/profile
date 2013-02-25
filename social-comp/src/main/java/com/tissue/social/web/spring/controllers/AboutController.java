package com.tissue.social.web.spring.controllers;

import com.tissue.core.command.Command;
import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.social.About;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.services.CommonService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Locale;

@Controller
public class AboutController {

    @Autowired
    protected UserService userService;

    @RequestMapping(value="/about", method=GET)
    public String about(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        List<About> abouts = userService.getAbouts();
        model.put("abouts", abouts);
        return "about";
    }


    @RequestMapping(value="/about/_create", method=POST)
    public String addAbout(@Valid Command command, BindingResult result, Map model) {

        /**
        About about = new About();
        about = aboutService.addAbout(about);

        model.put("about", about);
        */

        return "fragments/newAbout";
    }

}


