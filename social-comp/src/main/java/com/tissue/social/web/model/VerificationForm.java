package com.tissue.social.web.model;

import com.tissue.core.command.VerificationCommand;
import com.tissue.core.social.Account;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

public class VerificationForm implements VerificationCommand, Serializable {

    private String code;
    private String email;
    private Account account;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

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
