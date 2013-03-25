package com.tissue.social.web.model;

import com.tissue.core.Account;
import com.tissue.core.command.ResetCommand;
import java.io.Serializable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ResetRequestForm implements ResetCommand, Serializable {

    private String id;

    private String code;

    @NotEmpty(message="email cann't be empty")
    @Email(message="invalid email format")
    private String email;

    private Account account;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

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
