package com.tissue.social.web.model;

import com.tissue.core.User;
import com.tissue.core.Account;
import com.tissue.core.social.Invitation;
import com.tissue.core.command.InvitationCommand;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class InvitationForm implements InvitationCommand, Serializable {

    private String id;

    @NotEmpty(message="letter cann't be empty")
    private String content;

    private User to;

    private Account from;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getTo() {
        return to;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getFrom() {
        return from;
    }

}
