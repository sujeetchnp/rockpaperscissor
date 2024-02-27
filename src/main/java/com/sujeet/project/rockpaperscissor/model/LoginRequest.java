package com.sujeet.project.rockpaperscissor.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String playerName;
    private String password;
//
//    public LoginRequest(String playerName, String password) {
//        this.playerName = playerName;
//        this.password = password;
//    }

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
