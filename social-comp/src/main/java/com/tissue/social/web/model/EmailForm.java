package com.tissue.social.web.model;

import com.tissue.core.command.EmailCommand;
import com.tissue.core.social.Account;
import org.hibernate.validator.constraints.Email;
import java.io.Serializable;

public class EmailForm implements EmailCommand, Serializable {

    @Email(message="invalid email format")
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
