package com.tissue.social.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.plan.Plan;
import com.tissue.commons.services.ViewerService;
import com.tissue.social.Impression;
import com.tissue.social.web.model.ImpressionForm;
import com.tissue.social.services.ImpressionService;
import com.tissue.social.services.OwnerService;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.Set;
import java.util.Map;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ImpressionController {

    private static Logger logger = LoggerFactory.getLogger(ImpressionController.class);

    @Autowired
    private ImpressionService impressionService;

    @Autowired
    protected OwnerService ownerService;

    @Autowired
    protected ViewerService viewerService;

    @RequestMapping(value="/users/{userId}/impressions/_create")
    public String addImpression(@PathVariable("userId") User owner, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "impressions");
        model.put("owner", owner);
        model.put("invitable", viewerService.isInvitable(owner.getId(), viewerAccount));
        model.put("impressionForm", new ImpressionForm());

        return "createImpressionFormView";
    }

    /**
     * Add impression.
     */
    @RequestMapping(value="/users/{userId}/impressions/_create", method=POST)
    public String addImpression(@PathVariable("userId") User owner, @Valid ImpressionForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        if(result.hasErrors()) {
            model.put("selected", "impressions");
            model.put("owner", owner);
            model.put("invitable", viewerService.isInvitable(owner.getId(), viewerAccount));
            return "createImpressionFormView";
        }

        form.setTo(owner);
        form.setAccount(viewerAccount);
        impressionService.createImpression(form);

        model.clear();
        return "redirect:/users/" + form.getTo().getId().replace("#","") + "/impressions";
    }

    @RequestMapping(value="/users/{userId}/impressions")
    public String getImpressions(@PathVariable("userId") User owner, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "impressions");
        model.put("owner", owner);
        model.put("invitable", viewerService.isInvitable(owner.getId(), viewerAccount));

        List<Plan> plans = ownerService.getPlans(owner.getId());
        model.put("plans", plans);

        Boolean isFriend = ownerService.isFriend(owner.getId(), viewerAccount);
        model.put("isFriend", isFriend);

        List<Impression> impressions = impressionService.getImpressions(owner.getId());
        model.put("impressions", impressions);

        return "impressions";
    }

}
