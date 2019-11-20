package com.example.social_event;

public class User {

    String id;  //video me id hai so
    String username;
    String fullname;
    String email;
    String password;
    String boi;
    String imageurl;


    public User()
    {

    }

    public User(String id, String username,String fullname,String email, String password,String boi,String imageurl) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.boi = boi;
        this.imageurl = imageurl;
        this.email = email;
        this.password = password;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBoi() {
        return boi;
    }

    public void setBoi(String boi) {
        this.boi = boi;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
