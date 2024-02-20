package com.sujeet.project.rockpaperscissor.controller;

import com.sujeet.project.rockpaperscissor.model.*;
import com.sujeet.project.rockpaperscissor.repository.GameRepository;
import com.sujeet.project.rockpaperscissor.repository.GameRoundRepository;
import com.sujeet.project.rockpaperscissor.service.HistoryServiceImpl;
import com.sujeet.project.rockpaperscissor.service.RockPaperScissorImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private RockPaperScissorImpl rockPaperScissor;


    @Secured("ROLE_PLAYER")
    @PostMapping("/start")
    public ResponseEntity<StartGameResponse> startGame(@RequestBody StartGameRequest startGameRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String playerName = authentication.getName();
        Integer gameId = rockPaperScissor.startGame(playerName, startGameRequest.getMaxRounds());
        StartGameResponse response = new StartGameResponse("Game started successfully", gameId);
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @PostMapping("/play")
    public GameOutput playGame(@RequestBody GameInput gameInput){
        return rockPaperScissor.playRound(gameInput);
    }





}
