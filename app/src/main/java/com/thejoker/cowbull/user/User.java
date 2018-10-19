package com.thejoker.cowbull.user;

public class User {
    String userId;
    String name;
    int trophies;
    String password;

    public User(){

    }

    public User(String userId, String name, int trophies, String password) {
        this.userId = userId;
        this.name = name;
        this.trophies = trophies;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
