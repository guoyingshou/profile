package com.tissue.social.web.model;

import com.tissue.core.command.ImpressionCommand;
import com.tissue.core.social.User;
import com.tissue.core.social.Account;
import com.tissue.core.social.Impression;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class ImpressionForm implements ImpressionCommand, Serializable {

    @NotEmpty(message="letter cann't be empty")
    private String content;

    private String userId;
    private Account account;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
