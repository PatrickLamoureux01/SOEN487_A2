package com.example.rest.Core;

import java.io.Serializable;

public class Album implements Serializable {

    private String isrc;
    private String title;
    private String description;
    private int year;
    private Artist artist;

    public Album(String isrc, String title, String description, int year, Artist artist) {
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist = artist;
    }

    public Album() {

        this.isrc = null;
        this.title = null;
        this.description = null;
        this.year = 0;
        this.artist = null;

    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setArtist(Artist artist) { this.artist = artist; }

    public Artist getArtist() { return artist; }

    public String toString() {
        return String.format("Album Info: \n ISRC: %s \n Title: %s \n Description: %s \n Year: %s \n Artist: %s",this.getIsrc(), this.getTitle(), this.getDescription(), this.getYear(), this.getArtist());
    }

}

