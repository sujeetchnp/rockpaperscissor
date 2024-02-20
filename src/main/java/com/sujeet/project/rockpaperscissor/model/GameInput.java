package com.sujeet.project.rockpaperscissor.model;

public class GameInput {
    private Integer gameId;

    private Integer roundNumber;

    private Choice playerChoice;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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
}
