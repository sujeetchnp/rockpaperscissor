package com.sujeet.project.rockpaperscissor.service;

import com.sujeet.project.rockpaperscissor.entity.GameEntity;
import com.sujeet.project.rockpaperscissor.entity.GameRoundEntity;
import com.sujeet.project.rockpaperscissor.entity.PlayerEntity;
import com.sujeet.project.rockpaperscissor.exception.ExcessiveRoundsException;
import com.sujeet.project.rockpaperscissor.exception.InvalidInputException;
import com.sujeet.project.rockpaperscissor.model.Choice;
import com.sujeet.project.rockpaperscissor.model.GameInput;
import com.sujeet.project.rockpaperscissor.model.GameOutput;
import com.sujeet.project.rockpaperscissor.model.GameResult;
import com.sujeet.project.rockpaperscissor.repository.GameRepository;
import com.sujeet.project.rockpaperscissor.repository.GameRoundRepository;
import com.sujeet.project.rockpaperscissor.util.RpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class RockPaperScissorImpl implements RockPaperScissorService{

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameRoundRepository gameRoundRepository;

    @Override
    public Integer startGame(String playerName, Integer maxRounds) {

        if (maxRounds % 2 == 0){
            throw new InvalidInputException("maxRounds must be an odd number.");
        }

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerName(playerName);

        GameEntity newGame = new GameEntity();
        newGame.setPlayer(playerEntity);
        newGame.setGameDate(new Date());
        newGame.setMaxRounds(maxRounds);

        newGame = gameRepository.save(newGame);
        return newGame.getGameId();
    }

    @Transactional
    @Override
    public GameOutput playRound(GameInput gameInput) {
        Integer gameId = gameInput.getGameId();

        Optional<GameEntity> optionalGameEntity = gameRepository.findById(gameId);

        GameEntity gameEntity;
        if (optionalGameEntity.isPresent()) {
            gameEntity = optionalGameEntity.get();
        } else {
            throw new InvalidInputException("Game not found");
        }

        int roundNumber = gameEntity.getGameRound().size() + 1;

        if(roundNumber > gameEntity.getMaxRounds()){
            throw new ExcessiveRoundsException("Cannot play more than " +gameEntity.getMaxRounds() + " rounds");
        }
        Choice playerChoice = gameInput.getPlayerChoice();
        Choice computerChoice = computerChoice();
        GameResult roundResult = RpsUtil.compareChoice(playerChoice, computerChoice);

        GameRoundEntity round = new GameRoundEntity();
        round.setGame(gameEntity);
        round.setRoundNumber(roundNumber);
        round.setPlayerChoice(playerChoice);
        round.setComputerChoice(computerChoice);
        round.setRoundResult(roundResult);
        gameRoundRepository.save(round);

        GameOutput gameOutput = new GameOutput();
        if (roundNumber == gameEntity.getMaxRounds()) {
            GameResult gameResult = determineGameWinner(gameId);
            gameOutput.setGameResult(gameResult);
            gameOutput.setGameEnded(true);
        } else {
            gameOutput.setGameResult(roundResult);
            gameOutput.setGameEnded(false);
        }

        return gameOutput;
    }


    private GameResult determineGameWinner(Integer gameId){
        GameEntity game = null;
        Optional<GameEntity> gameEntityOptional = gameRepository.findById(gameId);

        if(gameEntityOptional.isPresent()){
            game = gameEntityOptional.get();
        }else {
            throw new IllegalArgumentException("Game not found with ID: " +gameId);
        }

        List<GameRoundEntity> rounds = gameRoundRepository.findByGame_GameId(gameId);

        int playerWins = 0;
        int computerWins = 0;

        int maxRounds = game.getMaxRounds();
        int majorityWins = (maxRounds / 2) + 1;

        GameResult gameResult = GameResult.DRAW;

        for (GameRoundEntity round : rounds) {
            if (round.getRoundResult() == GameResult.WIN) {
                playerWins++;
            } else if (round.getRoundResult() == GameResult.LOSE) {
                computerWins++;
            }
        }

        if (playerWins >= majorityWins){
            gameResult = GameResult.WIN;
        }else if (computerWins >= majorityWins){
            gameResult = GameResult.LOSE;
        }

        gameRepository.updateGameResult(gameId, gameResult);


        return gameResult;
    }

    private Choice computerChoice(){

        Choice[] choices = Choice.values();
        return choices[new Random().nextInt(choices.length)];
    }

}
