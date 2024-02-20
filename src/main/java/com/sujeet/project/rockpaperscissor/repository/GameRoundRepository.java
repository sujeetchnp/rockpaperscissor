package com.sujeet.project.rockpaperscissor.repository;

import com.sujeet.project.rockpaperscissor.entity.GameEntity;
import com.sujeet.project.rockpaperscissor.entity.GameRoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRoundRepository extends JpaRepository<GameRoundEntity, Integer> {
    List<GameRoundEntity> findByGame_GameId(Integer gameId);

    @Modifying
    @Query("DELETE GameRoundEntity gr WHERE gr.game.gameId = :gameIdParam")
    int deleteByGameId(@Param("gameIdParam") Integer gameId);
}
