package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.dao.AccountDao;
//import com.tissue.core.dao.VerificationDao;
//import com.tissue.core.dao.ResetDao;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.VerificationCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
/**
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
*/

import java.util.List;
import java.util.Locale;

@Component
//public class AccountService implements MessageSourceAware {
public class AccountService {

    /**
    private String from = "webmaster@tissue.com";

    @Autowired
    private MailSender mailSender;
    */

    @Autowired
    private AccountDao accountDao;

    /**
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
    */

    public String addUser(UserCommand userCommand) {
        return accountDao.create(userCommand);
    }

    public boolean isUsernameExist(String username) {
        return accountDao.isUsernameExist(username);
    }

    public Account getAccount(String accountId) {
        return accountDao.getAccount(accountId);
    }

    public void setVerified(String accountId) {
        accountDao.setVerified(accountId);
    }

    public Account getAccountByEmail(String email) {
        return accountDao.getAccountByEmail(email);
    }

    public boolean isEmailExist(String email) {
        return accountDao.isEmailExist(email);
    }

    public void updateEmail(EmailCommand command) {
        accountDao.updateEmail(command);
    }

    public void updatePassword(PasswordCommand command) {
        accountDao.updatePassword(command);
    }

}
