package com.tissue.social.web.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserForm implements Serializable {

    @NotNull(message="displayName cann't be null")
    private String displayName;

    @NotNull(message="email cann't be null")
    @Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message="Invalid email address.")
    private String email;

    @NotNull(message="password cann't be null")
    @Size(min=6)
    private String password;

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
