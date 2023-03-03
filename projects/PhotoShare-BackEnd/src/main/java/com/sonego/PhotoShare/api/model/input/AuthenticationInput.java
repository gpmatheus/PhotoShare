package com.sonego.PhotoShare.api.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationInput {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
