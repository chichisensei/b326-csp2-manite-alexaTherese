package com.joysistvi.RecordingApp.model;

public class Album {
    private int id;
    private String name;
    private int year;

    public Album(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Album(int id) {
        this.id = id;
    }

    public Album(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
