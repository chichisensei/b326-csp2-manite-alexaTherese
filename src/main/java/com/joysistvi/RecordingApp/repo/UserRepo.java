package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.model.User;

public interface UserRepo {
    boolean createUser(User user);
    User loginUser(User user);
    boolean existByUsername(String username);
    boolean isValidLogin(String username, String password);
}
