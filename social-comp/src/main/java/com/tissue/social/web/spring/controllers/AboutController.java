package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.About;
import com.tissue.commons.util.Pager;
import com.tissue.commons.services.ViewerService;
import com.tissue.social.web.model.AboutForm;
import com.tissue.social.services.AboutService;

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
    protected AboutService aboutService;

    @Autowired
    protected ViewerService viewerService;

    @RequestMapping(value="/about/_create")
    public String praiseFormView(Map model) {

        model.put("selected", "praise");

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount != null) {
            model.put("viewerAccount", viewerAccount);
        }
 
        model.put("aboutForm", new AboutForm());
        return "createAboutFormView";
    }

    @RequestMapping(value="/about/_create", method=POST)
    public String addPraise(@Valid AboutForm form, BindingResult result, Map model) {

        model.put("selected", "praise");

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount != null) {
            model.put("viewerAccount", viewerAccount);
        }
 
        if(result.hasErrors()) {
            return "createAboutFormView";
        }

        form.setAccount(viewerAccount);
        aboutService.createAbout(form);

        model.clear();
        return "redirect:/praise";
    }

    @RequestMapping(value="/praise", method=GET)
    public String listPraise(Map model) {

        model.put("selected", "praise");

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount != null) {
            model.put("viewerAccount", viewerAccount);
        }

        List<About> abouts = aboutService.getAbouts();
        model.put("abouts", abouts);

        return "praise";
    }

    @RequestMapping(value="/about", method=GET)
    public String about(Map model) {

        model.put("selected", "vision");

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount != null) {
            model.put("viewerAccount", viewerAccount);
        }

        return "about";
    }

}


