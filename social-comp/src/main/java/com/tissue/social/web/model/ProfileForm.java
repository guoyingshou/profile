package com.tissue.social.web.model;

import com.tissue.core.Account;
import com.tissue.core.command.ProfileCommand;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class ProfileForm implements ProfileCommand, Serializable {

    @NotEmpty(message="displayName cann't be empty")
    private String displayName;

    @NotEmpty(message="headline cann't be empty")
    private String headline;

    private Account account;

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
}
