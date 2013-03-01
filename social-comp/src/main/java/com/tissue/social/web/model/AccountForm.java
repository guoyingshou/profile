package com.tissue.social.web.model;

import com.tissue.core.command.UserCommand;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
import java.nio.charset.Charset;
import com.google.common.hash.Hashing;
*/

public class AccountForm implements UserCommand, Serializable {

    private String id;

    @NotEmpty(message="{Username cann't by empty}")
    private String username;

    @Size(min=6, max=16)
    private String password;

    private String confirm;

    @NotEmpty(message="DisplayName cann't by empty")
    private String displayName;

    @NotEmpty(message="Headline cann't by empty")
    private String headline;

    @NotEmpty(message="Email cann't by empty")
    @Email(message="Email is invalid or already taken")
    private String email;

    /**-----------------------------*/
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        //this.password = Hashing.md5().hashString(password, Charset.forName("utf-8")).toString();
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
