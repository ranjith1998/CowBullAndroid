package com.thejoker.cowbull.user;

public class MultiplayerGame2 {
    int gameId;
    String user1;
    String user2;
    String number;
    int user1Entries;
    int user2Entries;
    boolean gameOn;
    int win;

    public MultiplayerGame2() {
    }

    public MultiplayerGame2(int gameId, String user1, String user2, String number, int user1Entries, int user2Entries, boolean gameOn, int win) {
        this.gameId = gameId;
        this.user1 = user1;
        this.user2 = user2;
        this.number = number;
        this.user1Entries = user1Entries;
        this.user2Entries = user2Entries;
        this.gameOn = gameOn;
        this.win = win;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getUser1Entries() {
        return user1Entries;
    }

    public void setUser1Entries(int user1Entries) {
        this.user1Entries = user1Entries;
    }

    public int getUser2Entries() {
        return user2Entries;
    }

    public void setUser2Entries(int user2Entries) {
        this.user2Entries = user2Entries;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}
