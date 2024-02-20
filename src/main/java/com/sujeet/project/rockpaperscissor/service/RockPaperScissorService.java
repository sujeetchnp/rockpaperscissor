package com.sujeet.project.rockpaperscissor.service;

import com.sujeet.project.rockpaperscissor.entity.PlayerEntity;
import com.sujeet.project.rockpaperscissor.model.Choice;
import com.sujeet.project.rockpaperscissor.model.GameInput;
import com.sujeet.project.rockpaperscissor.model.GameOutput;


public interface RockPaperScissorService {
    Integer startGame(String playerName, Integer maxRounds);
    GameOutput playRound(GameInput gameInput);
}
