package com.tissue.social.web.model;

import com.tissue.core.Account;
import com.tissue.core.command.EmailCommand;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

public class EmailForm implements EmailCommand, Serializable {

    @Email(message="invalid email format")
    @NotEmpty(message="email cann't be empty")
    private String email;

    private Account account;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
