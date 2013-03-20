package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.dao.AccountDao;
import com.tissue.core.dao.VerificationDao;
import com.tissue.core.dao.ResetDao;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.VerificationCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;
import com.tissue.core.command.ResetRequestCommand;
import com.tissue.core.command.ResetPasswordCommand;

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
public class AccountService implements MessageSourceAware {

    private String from = "webmaster@tissue.com";

    @Autowired
    private MailSender mailSender;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private VerificationDao verificationDao;

    @Autowired
    private ResetDao resetDao;

    private MessageSource messageSource;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String addUser(UserCommand userCommand) {
        return accountDao.create(userCommand);
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

    public String getAccountId(String code) {
        return verificationDao.getAccountId(code);
    }

    public void setVerified(String accountId) {
        verificationDao.setVerified(accountId);
    }

    public boolean isUsernameExist(String username) {
        return accountDao.isUsernameExist(username);
    }

    public Account getAccount(String accountId) {
        return accountDao.getAccount(accountId);
    }
 
    public boolean isCodeExist(String code) {
        return resetDao.isCodeExist(code);
    }

    public boolean isEmailExist(String email) {
        return accountDao.isEmailExist(email);
    }

    public String getEmail(String code) {
        return resetDao.getEmail(code);
    }

    public void updatePassword(ResetPasswordCommand command) {
        resetDao.updatePassword(command);
    }

}
