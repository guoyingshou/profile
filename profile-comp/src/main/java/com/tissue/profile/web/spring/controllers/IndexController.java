package com.tissue.profile.web.spring.controllers;

import com.tissue.profile.service.InvitationService;
import com.tissue.domain.profile.Invitation;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.security.core.userdetails.UserDetailsImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.sql.OCommandSQL;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    //@Qualifier("userService")
    private InvitationService invitationService;

    @RequestMapping(value="/home")
    public String index(Map model) {

        String viewerId = SecurityUtil.getUserId();

        if(viewerId != null) {
            //List<Invitation> invitations = invitationService.getInvitations(viewerId);
            int count = invitationService.getInvitationsCount(viewerId);
            if(count > 0) {
                model.put("invitationsCount", count);
            }
        }

        UserDetailsImpl viewer = SecurityUtil.getUser();

        if(viewer == null) {
            return "home";
        }
        else {
            model.put("viewer", viewer);
            return "dashboard";
        }
    }

    @RequestMapping(value="/dashboard")
    public String dashboard(Map model) {

        String viewerId = SecurityUtil.getUserId();
        int count = invitationService.getInvitationsCount(viewerId);
        if(count > 0) {
            model.put("invitationsCount", count);
        }
 
        model.put("viewer", SecurityUtil.getUser());
        return "dashboard";
    }

    @RequestMapping(value="/test1")
    public String test1(Principal principal, Map model) {
        return "test1";
    }

    @RequestMapping(value="/test2")
    public String test2() throws Exception {

        String name = "/opt/public/test/tianji-user-data/tmp/t1.csv";

        ODatabaseDocumentTx db = ODatabaseDocumentPool.global().acquire("remote:www.tissue.com/test1", "guo", "guo");
        try {
            ODocument doc = new ODocument("Person");

            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
            String line = r.readLine();

            int count = 1;
            while((line = r.readLine()) != null) {
                System.out.println(count++);

                String[] token = line.split(",");

                doc.reset();
                doc.field("id", token[0]);
                doc.field("email", token[1]);
                doc.field("displayName", token[4]);
                doc.field("headline", token[5]);
                doc.field("location", token[6]);
                doc.save("Person");

                System.out.println(doc);
            
                if(count > 10) 
                    break;
            }
        }
        finally {
            db.close();
        }

        return "test1";
    }

    @RequestMapping(value="/test3")
    public String test3() throws Exception {

        String name = "/opt/public/test/tianji-user-data/tmp/u1.csv";

        ODatabaseDocumentTx db = ODatabaseDocumentPool.global().acquire("remote:www.tissue.com/test1", "guo", "guo");
        try {

            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
            String line = r.readLine();

            long count = 1L;
            Date start = new Date();
            while((line = r.readLine()) != null) {

                String[] token = line.split(",");
                if(token.length != 7) {
                    //System.out.println(count + " line skipped..." + line);
                    continue;
                }

                Map<String, String> params = new HashMap();
                params.put("id", token[0]);
                params.put("email", token[1]);
                params.put("displayName", token[4]);
                params.put("headline", token[5]);
                params.put("location", token[6]);

                String qstr = "insert into person set id = :id, email = :email, displayName = :displayName, headline = :headline, location = :location";

                OCommandSQL cmd = new OCommandSQL(qstr);
                ODocument ret = db.command(cmd).execute(params);

                //System.out.println(ret);

                if((++count % 1000L) == 0) { 
                    System.out.println(new Date() +":" + count);
                }
            }
            Date end = new Date();
            System.out.println("start: " + start);
            System.out.println("end: " + end);

            System.out.println("total: " + count);
        }
        finally {
            db.close();
        }

        return "test1";
    }


}


