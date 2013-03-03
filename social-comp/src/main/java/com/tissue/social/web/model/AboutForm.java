package com.tissue.social.web.model;

import com.tissue.core.command.AboutCommand;
import com.tissue.core.social.User;
import com.tissue.core.social.Account;
import com.tissue.core.social.About;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class AboutForm implements AboutCommand, Serializable {

    @NotEmpty(message="content cann't be empty")
    private String content;

    private Account account;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
