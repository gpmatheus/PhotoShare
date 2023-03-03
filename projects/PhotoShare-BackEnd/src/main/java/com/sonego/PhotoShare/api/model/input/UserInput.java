package com.sonego.PhotoShare.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserInput {

    @NotNull(message = "email is obligatory")
    @Email(message = "email is not valid")
    private String email;

    @NotBlank(message = "password is obligatory")
    private String password;

    @NotBlank(message = "username is obligatory")
    private String username;
}
