package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.User;

public interface UserService {
    boolean createUser(User user);
    User loginUser(User user);

}
