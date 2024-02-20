package com.sujeet.project.rockpaperscissor.repository;

import com.sujeet.project.rockpaperscissor.entity.GameEntity;
import com.sujeet.project.rockpaperscissor.model.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

    @Modifying
    @Query("UPDATE GameEntity SET gameResult = :gameResultParam WHERE gameId = :gameIdParam")
    int updateGameResult(@Param("gameIdParam") Integer gameId,@Param("gameResultParam") GameResult gameResult);


    List<GameEntity> findByPlayer_PlayerName(String playerName);

    // OR to get history
    @Query("SELECT g FROM GameEntity g WHERE g.player.playerName = :playerName")
    List<GameEntity> historyByPlayerName(String playerName);

    @Modifying
    @Query("DELETE FROM GameEntity g WHERE g.player.playerName = :playerName")
    int deleteByPlayerName(@Param("playerName") String PlayerName);


}
