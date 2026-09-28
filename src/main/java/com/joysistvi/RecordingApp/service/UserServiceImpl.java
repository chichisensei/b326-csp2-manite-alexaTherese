package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.User;
import com.joysistvi.RecordingApp.repo.UserRepo;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean createUser(User user) {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            System.out.println("Username is required!");
            return false;
        } else if(user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Password is required!");
            return false;
        } else if (userRepo.existByUsername(user.getUserName())) {
            System.out.println("Username exists!");
            return false;
        }
        return userRepo.createUser(user);
    }

    @Override
    public User loginUser(User user) {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            System.out.println("Username is required!");
            return null;
        } else if(user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Password is required!");
            return null;
        } else if (!userRepo.isValidLogin(user.getUserName(), user.getPassword())) {
            System.out.println("Invalid username or password!");
            return null;
        }
        return userRepo.loginUser(user);
    }


}
