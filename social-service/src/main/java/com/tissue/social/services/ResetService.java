package com.tissue.social.services;

import com.tissue.core.command.ResetRequestCommand;
import com.tissue.core.command.ResetPasswordCommand;
import com.tissue.core.social.dao.ResetDao;

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
public class ResetService implements MessageSourceAware {

    private String from = "webmaster@tissue.com";

    @Autowired
    private ResetDao resetDao;

    @Autowired
    private MailSender mailSender;

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isEmailExist(String email) {
        return resetDao.isEmailExist(email);
    }

    public boolean isCodeExist(String code) {
        return resetDao.isCodeExist(code);
    }

    @Async
    public void sendResetEmail(ResetRequestCommand command, Locale locale) {
        resetDao.create(command);

        String subject = messageSource.getMessage("Subject.reset", new Object[]{}, locale);
        
        Object[] args = {command.getCode()};
        String text = messageSource.getMessage("Text.reset", args, locale);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom(from);
        msg.setTo(command.getEmail());

        mailSender.send(msg);
    }

    public String getEmail(String code) {
        return resetDao.getEmail(code);
    }

    public void updatePassword(ResetPasswordCommand command) {
        resetDao.updatePassword(command);
    }
}
