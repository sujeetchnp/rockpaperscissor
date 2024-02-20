package com.sujeet.project.rockpaperscissor.util;

import com.sujeet.project.rockpaperscissor.exception.InvalidInputException;
import com.sujeet.project.rockpaperscissor.model.Choice;
import com.sujeet.project.rockpaperscissor.model.GameResult;

public class RpsUtil {
    public static GameResult compareChoice(Choice playerChoice, Choice computerChoice){
        if (playerChoice == computerChoice){
            return GameResult.DRAW;
        }
        switch (playerChoice){
            case ROCK:
                return computerChoice == Choice.SCISSOR? GameResult.WIN : GameResult.LOSE;
            case PAPER:
                return computerChoice == Choice.ROCK? GameResult.WIN : GameResult.LOSE;
            case SCISSOR:
                return computerChoice == Choice.PAPER? GameResult.WIN: GameResult.LOSE;
            default:
                throw new InvalidInputException("Invalid Input: " +playerChoice);
        }
    }
}
