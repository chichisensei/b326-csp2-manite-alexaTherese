package com.joysistvi.RecordingApp.model;

public class Song {
    private int id;
    private String title;
    private String length;
    private String genre;
    private String name;
    private int album_id;


    public Song(int id, String title, String length, String genre) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.genre = genre;
    }

    public Song(String title, String length, String genre) {
        this.title = title;
        this.length = length;
        this.genre = genre;
    }

    public Song(int id) {
        this.id = id;
    }

    public Song(String name, String title, String length, String genre) {
        this.name = name;
        this.title = title;
        this.length = length;
        this.genre = genre;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
