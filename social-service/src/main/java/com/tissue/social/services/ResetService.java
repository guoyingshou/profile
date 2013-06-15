package com.tissue.social.services;

import com.tissue.core.dao.ResetDao;
import com.tissue.core.command.ResetCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ResetService implements MessageSourceAware {

    private static Logger logger = LoggerFactory.getLogger(ResetService.class);

    @Value("${mail_from}")
    private String from;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ResetDao resetDao;

    private MessageSource messageSource;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Async
    public void sendResetEmail(ResetCommand command, Locale locale) {
        resetDao.create(command);

        String subject = messageSource.getMessage("Subject.email.reset", new Object[]{}, locale);
        
        Object[] args = {command.getCode()};
        String text = messageSource.getMessage("Text.email.reset", args, locale);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom(from);
        msg.setTo(command.getAccount().getEmail());

        try {
            mailSender.send(msg);
        }
        catch(Exception exc) {
            logger.warn(exc.getMessage());
        }
    }

    public void deleteReset(String resetId) {
        resetDao.delete(resetId);
    }

}
