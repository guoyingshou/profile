package com.tissue.profile.web.spring.controllers;

import com.tissue.profile.web.model.UserForm;
import com.tissue.profile.web.model.AccountForm;
import com.tissue.domain.profile.User;
import com.tissue.profile.service.UserService;

import org.springframework.stereotype.Controller;
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
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.Set;
import java.util.Map;
import java.nio.charset.Charset;

import com.google.common.hash.Hashing;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SignupController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @RequestMapping(value="/login")
    public String loginForm(Map model, Locale locale) {
        return "login";
    }

    @RequestMapping(value="/signup")
    public String signupForm(Map model, Locale locale) {
        model.put("account", new AccountForm());
        return "signup";
    }

    /**
     * Signup.
     */
    @RequestMapping(value="/signup", method=POST)
    public String signup(@Valid @ModelAttribute("account") AccountForm form, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println(error);
            }
            return "signup";
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setDisplayName(form.getDisplayName());
        user.setEmail(form.getEmail());

        String md5 = Hashing.md5().hashString(form.getPassword(), Charset.forName("utf-8")).toString();
        user.setPassword(md5);

        user.setCreateTime(new Date());

        userService.addUser(user);
        return "redirect:/dashboard";
    }

}
