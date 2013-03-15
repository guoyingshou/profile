package com.tissue.social.services;

import com.tissue.core.Account;
import com.tissue.core.dao.AccountDao;
import com.tissue.core.dao.VerificationDao;
import com.tissue.core.command.UserCommand;
import com.tissue.core.command.VerificationCommand;
import com.tissue.core.command.EmailCommand;
import com.tissue.core.command.PasswordCommand;

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

    private MessageSource messageSource;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
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

    public String addUser(UserCommand userCommand) {
        return accountDao.create(userCommand);
    }

    public boolean isUsernameExist(String username) {
        return accountDao.isUsernameExist(username);
    }

    public boolean isEmailExist(String email) {
        return accountDao.isEmailExist(email);
    }

    public Account getAccount(String accountId) {
        return accountDao.getAccount(accountId);
    }
 
    /**
    public Boolean isFriend(String userId, Account viewerAccount) {
        if(viewerAccount == null) {
            return false;
        }
        return userDao.isFriend(userId, viewerAccount.getUser().getId());
    }

    public List<User> getFriends(String userId) {
        return userDao.getFriends(userId);
    }

    public boolean isEmailExist(String excludingUserId, String email) {
        return userDao.isEmailExist(excludingUserId, email);
    }

    public List<Activity> getSelfActivities(String userId, int count) {
        return activityDao.getSelfActivities(userId, count);
    }

    public List<Topic> getNewTopics(String excludingUserId, int limit) {
        return topicDao.getNewTopics(excludingUserId, limit);
    }

    public List<Plan> getPlansByUser(String userId) {
        return planDao.getPlansByUser(userId);
    }

    public List<Plan> getPlansByAccount(String accountId) {
        return planDao.getPlansByAccount(accountId);
    }

    public long getPostsCountByUser(String userId) {
        return postDao.getPostsCountByUser(userId);
    }

    public List<Post> getPagedPostsByUser(String userId, int page, int size) {
        return postDao.getPagedPostsByUser(userId, page, size);
    }

    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    public User getUserByAccount(String accountId) {
        return userDao.getUserByAccount(accountId);
    }

    public String getUserIdByAccount(String accountId) {
        return userDao.getUserIdByAccount(accountId);
    }
    */

}
