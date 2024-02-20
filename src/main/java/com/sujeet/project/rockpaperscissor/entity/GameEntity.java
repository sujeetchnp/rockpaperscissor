package com.sujeet.project.rockpaperscissor.entity;

import com.sujeet.project.rockpaperscissor.model.GameResult;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gameId;

    @Column(name = "game_date")
    private Date gameDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_result")
    private GameResult gameResult;

    @Column(name = "max_rounds")
    private Integer maxRounds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playername")
    private PlayerEntity player;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GameRoundEntity> gameRound;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public List<GameRoundEntity> getGameRound() {
        return gameRound;
    }

    public void setGameRound(List<GameRoundEntity> gameRound) {
        this.gameRound = gameRound;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public Integer getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(Integer maxRounds) {
        this.maxRounds = maxRounds;
    }
}
