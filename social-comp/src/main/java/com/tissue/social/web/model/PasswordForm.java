package com.tissue.social.web.model;

import com.tissue.core.command.PasswordCommand;
import com.tissue.core.social.Account;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.nio.charset.Charset;
import com.google.common.hash.Hashing;

public class PasswordForm implements PasswordCommand, Serializable {

    @NotEmpty(message="password cann't be null")
    @Size(min=6, max=16)
    private String password;
    private String confirm;
    private Account account;

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

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
