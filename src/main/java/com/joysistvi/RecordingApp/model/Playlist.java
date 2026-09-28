package com.joysistvi.RecordingApp.model;

public class Playlist {
    private int id;
    private String name;
    private String date_created;
    private int user_id;

    public Playlist(int id, String name, String date_created, int user_id) {
        this.id = id;
        this.name = name;
        this.date_created = date_created;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
