package com.sujeet.project.rockpaperscissor.model;

public class StartGameResponse {
    private String message;

    private Integer gameId;

    public StartGameResponse(String message, Integer gameId) {
        this.message = message;
        this.gameId = gameId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
