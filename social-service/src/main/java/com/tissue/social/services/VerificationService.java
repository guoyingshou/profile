package com.tissue.social.services;

import com.tissue.core.dao.VerificationDao;
import com.tissue.core.command.VerificationCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Locale;

@Component
public class VerificationService implements MessageSourceAware {

    private String from = "webmaster@tissue.com";

    @Autowired
    private VerificationDao verificationDao;

    @Autowired
    private MailSender mailSender;

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Async
    public void sendVerificationEmail(VerificationCommand command, Locale locale) {

        //generate and save verification code entry
        verificationDao.create(command);

        String subject = messageSource.getMessage("Subject.verification", new Object[]{}, locale);
        
        Object[] args = {command.getCode()};
        String text = messageSource.getMessage("Text.verification", args, locale);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom(from);
        msg.setTo(command.getEmail());

        mailSender.send(msg);
    }

    public String getAccountId(String code) {
        return verificationDao.getAccountId(code);
    }

    public void setVerified(String accountId) {
        verificationDao.setVerified(accountId);
    }

}
