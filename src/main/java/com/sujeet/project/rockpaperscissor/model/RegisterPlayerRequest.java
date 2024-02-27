package com.sujeet.project.rockpaperscissor.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RegisterPlayerRequest {
    private String playerName;
    private String password;
    private String email;
    private String city;
    private String country;


//    public RegisterPlayerRequest(String playerName, String password, String email, String city, String country) {
//        this.playerName = playerName;
//        this.password = password;
//        this.email = email;
//        this.city = city;
//        this.country = country;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
