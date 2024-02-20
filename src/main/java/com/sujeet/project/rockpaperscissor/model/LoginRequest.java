package com.sujeet.project.rockpaperscissor.model;

public class LoginRequest {
    private String playerName;
    private String password;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
