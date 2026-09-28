package com.joysistvi.RecordingApp.controller;

import com.joysistvi.RecordingApp.model.User;
import com.joysistvi.RecordingApp.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean handleCreateUser(User user) {
        return userService.createUser(user);
    }
    public User handleLoginUser(User user) {
        return userService.loginUser(user);
    }
}
