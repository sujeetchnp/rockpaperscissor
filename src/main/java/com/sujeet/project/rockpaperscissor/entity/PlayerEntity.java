package com.sujeet.project.rockpaperscissor.entity;

import com.sujeet.project.rockpaperscissor.model.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "player")
public class PlayerEntity {
    @Id
    @Column(name = "playername")
    private String playerName;
    private String password;
    private String email;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role userRole;
    @Column(name = "last_logged_in_date")
    private Date lastLoggedInDate;

    @Column(name = "registered_date", nullable = false, updatable = false)
    private Date registeredDate;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameEntity> game_results;


    public String getPlayerName() {
        return playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getLastLoggedInDate() {
        return lastLoggedInDate;
    }

    public void setLastLoggedInDate(Date lastLoggedInDate) {
        this.lastLoggedInDate = lastLoggedInDate;
    }

    public List<GameEntity> getGame_results() {
        return game_results;
    }

    public void setGame_results(List<GameEntity> game_results) {
        this.game_results = game_results;
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

    public Date getRegisteredDate() {
        return registeredDate;
    }

    @PrePersist
    protected void onPrePersist(){
        this.registeredDate = new Date();
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
