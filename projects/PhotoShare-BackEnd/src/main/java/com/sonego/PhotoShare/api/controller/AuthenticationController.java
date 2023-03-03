package com.sonego.PhotoShare.api.controller;

import com.sonego.PhotoShare.api.model.UserModel;
import com.sonego.PhotoShare.api.model.input.AuthenticationInput;
import com.sonego.PhotoShare.api.model.input.UserInput;
import com.sonego.PhotoShare.api.wrapper.IUserWrapper;
import com.sonego.PhotoShare.business.model.User;
import com.sonego.PhotoShare.business.service.AuthenticationService;
import com.sonego.PhotoShare.business.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    private IUserService userService;

    private IUserWrapper userWrapper;

    @PostMapping
    public ResponseEntity<UserModel> authenticate(@RequestBody @Valid AuthenticationInput authenticationInput) {
        String token = authenticationService
                .authenticate(authenticationInput.getUsername(), authenticationInput.getPassword());
        UserModel userModel = userWrapper.toModel(userService.getByUsername(authenticationInput.getUsername()));
        userModel.setToken(token);
        userModel.setProfiles(null);
        return ResponseEntity.ok(userModel);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody @Valid UserInput userInput) {
        User user = userWrapper.toUser(userInput);
        User resultingUser = authenticationService.register(user);
        resultingUser.setToken(authenticationService.authenticate(userInput.getUsername(), userInput.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userWrapper.toModel(resultingUser));
    }

    @GetMapping(value = "/isValid/{username}/{token}")
    public ResponseEntity<Map<String, Boolean>> isTokenValid(@PathVariable String username,
                                                             @PathVariable String token) {
        boolean isValid = authenticationService.isTokenValid(token, username);
        var response = new HashMap<String, Boolean>();
        response.put("isValid", isValid);
        return ResponseEntity.ok(response);
    }
}
