package com.sonego.PhotoShare.api.controller;

import com.sonego.PhotoShare.api.model.UserModel;
import com.sonego.PhotoShare.api.model.input.UserInput;
import com.sonego.PhotoShare.api.wrapper.IUserWrapper;
import com.sonego.PhotoShare.business.model.User;
import com.sonego.PhotoShare.business.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    private IUserWrapper userWrapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id) {
        UserModel user = userWrapper.toModel(userService.getUserById(id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/setAdmin/{userId}")
    public ResponseEntity<UserModel> addAdminPermission(@PathVariable UUID userId) {
        return ResponseEntity.ok(userWrapper.toModel(userService.addAdminPermission(userId)));
    }
}
