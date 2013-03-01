package com.tissue.social.web.model;

import com.tissue.core.command.InvitationCommand;
import com.tissue.core.social.User;
import com.tissue.core.social.Account;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class InvitationForm implements InvitationCommand, Serializable {

    @NotEmpty(message="letter cann't be empty")
    private String letter;

    private String userId;
    private Account account;

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
