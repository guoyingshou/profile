package com.tissue.social.web.model;

import com.tissue.core.Account;
import com.tissue.core.About;
import com.tissue.core.command.ContentCommand;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class AboutForm implements ContentCommand, Serializable {

    private String id;

    @NotEmpty(message="content cann't be empty")
    private String content;

    private Account account;

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

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
