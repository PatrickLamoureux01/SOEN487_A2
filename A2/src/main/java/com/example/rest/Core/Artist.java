package com.example.rest.Core;

public class Artist {

    private String nickname;
    private String fName;
    private String lName;
    private String bio;

    public Artist() {
        this.nickname = null;
        this.fName = null;
        this.lName = null;
        this.bio = null;
    }

    public Artist(String fname, String lname, String nick, String bio) {
        this.nickname = nick;
        this.fName = fname;
        this.lName = lname;
        this.bio = bio;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String toString() {
        return String.format("Artist Info - Nickname: %s, First Name: %s, Last Name: %s, Bio: %s", this.getNickname(), this.getfName(), this.getlName(), this.getBio());
    }
}
