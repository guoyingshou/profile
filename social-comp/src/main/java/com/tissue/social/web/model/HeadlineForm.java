package com.tissue.social.web.model;

import com.tissue.core.Account;
import com.tissue.core.command.UserCommand;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HeadlineForm implements UserCommand, Serializable {

    private String id;

    @NotEmpty(message="DisplayName cann't by empty")
    @Size(max=16)
    private String displayName;

    @NotEmpty(message="Headline cann't by empty")
    @Size(max=128)
    private String headline;

    private Account account;

    /**-----------------------------*/
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public String getStatus() {
        return null;
    }
}
