package com.joysistvi.RecordingApp.model;

public class User {

    private String userName;
    private String password;
    private int id;
    private boolean is_admin;

    public User(int id, String userName, String password, boolean is_admin) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.is_admin = is_admin;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {

    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
