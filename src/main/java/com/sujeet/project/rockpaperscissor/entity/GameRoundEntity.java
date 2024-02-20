package com.sujeet.project.rockpaperscissor.entity;

import javax.persistence.*;
import com.sujeet.project.rockpaperscissor.model.Choice;
import com.sujeet.project.rockpaperscissor.model.GameResult;

@Entity
@Table(name = "game_round")
public class GameRoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "round_number")
    private Integer roundNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_choice")
    private Choice playerChoice;

    @Enumerated(EnumType.STRING)
    @Column(name = "computer_choice")
    private Choice computerChoice;
    @Enumerated(EnumType.STRING)
    @Column(name = "round_result")
    private GameResult roundResult;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private GameEntity game;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(Choice playerChoice) {
        this.playerChoice = playerChoice;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public Choice getComputerChoice() {
        return computerChoice;
    }

    public void setComputerChoice(Choice computerChoice) {
        this.computerChoice = computerChoice;
    }

    public GameResult getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(GameResult roundResult) {
        this.roundResult = roundResult;
    }
}
