package com.sujeet.project.rockpaperscissor.controller;

import com.sujeet.project.rockpaperscissor.entity.GameEntity;
import com.sujeet.project.rockpaperscissor.model.GameHistoryModel;
import com.sujeet.project.rockpaperscissor.model.LoginRequest;
import com.sujeet.project.rockpaperscissor.model.RegisterPlayerRequest;
import com.sujeet.project.rockpaperscissor.repository.GameRepository;
import com.sujeet.project.rockpaperscissor.service.HistoryServiceImpl;
import com.sujeet.project.rockpaperscissor.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;

    @Autowired
    private HistoryServiceImpl historyService;

    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/register")
    public String registerPlayer(@RequestBody RegisterPlayerRequest registerPlayerRequest){
        playerService.register(registerPlayerRequest);
        return "Player registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginRequest loginRequest){
        return playerService.login(loginRequest);
    }

    @Secured("ROLE_PLAYER")
    @GetMapping("/history")
    public ResponseEntity<List<GameHistoryModel>> getHistory(Authentication authentication){
        String playerName = authentication.getName();
        List<GameHistoryModel> history = historyService.getAllHistory(playerName);
        return ResponseEntity.ok(history);
    }
    
    @Secured("ROLE_PLAYER")
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllPlayerHistory(Authentication authentication){
        String playerName = authentication.getName();
        List<GameEntity> games = gameRepository.findByPlayer_PlayerName(playerName);
        for (GameEntity game : games) {
            historyService.deleteRoundHistoryByGameId(game.getGameId());
        }
        historyService.deleteHistory(playerName);
        return ResponseEntity.ok("Complete player history deleted successfully.");
    }


}
