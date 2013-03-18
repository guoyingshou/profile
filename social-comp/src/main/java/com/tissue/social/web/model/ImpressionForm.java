package com.tissue.social.web.model;

import com.tissue.core.User;
import com.tissue.core.Account;
import com.tissue.core.social.Impression;
import com.tissue.core.command.ImpressionCommand;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class ImpressionForm implements ImpressionCommand, Serializable {

    private String id;

    @NotEmpty(message="letter cann't be empty")
    private String content;

    private Account account;

    private User to;
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getTo() {
        return to;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
