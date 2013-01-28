package com.tissue.social.web.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AccountForm {

    @NotEmpty(message="{account.username}")
    private String username;

    @NotEmpty(message="password cann't be null")
    @Size(min=6)
    private String password;

    private String confirm;

    @NotEmpty(message="displayName cann't be null")
    private String displayName;

    private String headline;

    @NotEmpty(message="email cann't be null")
    @Email
    private String email;

    /**-----------------------------*/
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getConfirm() {
        return confirm;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
