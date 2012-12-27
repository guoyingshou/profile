package com.tissue.profile.web.spring.controllers;

import com.tissue.domain.social.Event;
import com.tissue.domain.profile.Invitation;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.security.core.userdetails.UserDetailsImpl;
import com.tissue.commons.service.EventService;
import com.tissue.profile.service.InvitationService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.sql.OCommandSQL;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.security.Principal;
import java.nio.charset.Charset;

import com.google.common.hash.Hashing;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexController {

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private EventService eventService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @RequestMapping(value="/home")
    public String index(Map model, Locale locale) {
        //model.put("locale", locale.toString());

        UserDetailsImpl viewer = SecurityUtil.getUser();

        if(viewer == null) {
            List<Event> events = eventService.getLatestEvents();
            model.put("events", events);
            return "home";
        }
        else {
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model, Locale locale) {
        //model.put("locale", locale.toString());

        String viewerId = SecurityUtil.getUserId();
        List<Event> events = eventService.getTopicRelatedEvents(viewerId);
        model.put("events", events);

        List<Invitation> invitations = invitationService.getInvitations(viewerId);
        model.put("invitationsCount", invitations.size());

        model.put("viewer", SecurityUtil.getUser());
        return "dashboard";
    }

}


