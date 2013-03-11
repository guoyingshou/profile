package com.tissue.social.web.model;

import com.tissue.core.command.ResetRequestCommand;
import java.io.Serializable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ResetRequestForm implements ResetRequestCommand, Serializable {

    private String code;

    @NotEmpty(message="email cann't be empty")
    @Email(message="invalid email format")
    private String email;

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

}
