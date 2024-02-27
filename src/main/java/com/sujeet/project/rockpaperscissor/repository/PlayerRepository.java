package com.sujeet.project.rockpaperscissor.repository;

import com.sujeet.project.rockpaperscissor.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {

    PlayerEntity findByPlayerName(String playerName);

    @Modifying
    @Query("UPDATE PlayerEntity set lastLoggedInDate = :dateParam WHERE playerName = :playerNameParam")
    void updateLoggedInDate(@Param("playerNameParam") String playerName, @Param("dateParam") Date lastLoggedInDate);


}
