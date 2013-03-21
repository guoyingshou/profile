package com.tissue.social.web.spring.controllers;

import com.tissue.core.Verification;
import com.tissue.social.services.AccountService;
import com.tissue.social.services.VerificationService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class VerificationController {

    private static Logger logger = LoggerFactory.getLogger(VerificationController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private VerificationService verificationService;

    @RequestMapping(value="/verifications/{code}")
    public String verifyCode(@PathVariable("code") Verification verification, Map model) {
        if(verification == null) {
            throw new IllegalArgumentException("invalid verification code");
        }

        accountService.setVerified(verification.getAccount().getId());
        verificationService.deleteVerification(verification.getId());

        return "verificationSuccess";
    }
}
