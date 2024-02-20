package com.sujeet.project.rockpaperscissor.service;

import com.sujeet.project.rockpaperscissor.entity.GameEntity;
import com.sujeet.project.rockpaperscissor.entity.GameRoundEntity;
import com.sujeet.project.rockpaperscissor.model.GameHistoryModel;
import com.sujeet.project.rockpaperscissor.repository.GameRepository;
import com.sujeet.project.rockpaperscissor.repository.GameRoundRepository;
import com.sujeet.project.rockpaperscissor.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameRoundRepository gameRoundRepository;

    @Override
    public List<GameHistoryModel> getAllHistory(String playerName) {
        List<GameEntity> games = gameRepository.findByPlayer_PlayerName(playerName);
        List<GameHistoryModel> gameHistoryModels = new ArrayList<>();
        for (GameEntity gameEntity : games){
            GameHistoryModel model = new GameHistoryModel();
            model.setGameId(gameEntity.getGameId());
            model.setGameDate(gameEntity.getGameDate());
            if (gameEntity.getGameResult() != null){
                model.setGameResult(gameEntity.getGameResult());
            }
            gameHistoryModels.add(model);
        }

        return gameHistoryModels;
    }

    @Override
    @Transactional
    public int deleteHistory(String playerName) {
         return gameRepository.deleteByPlayerName(playerName);
    }

    @Override
    @Transactional
    public int deleteRoundHistoryByGameId(Integer gameId) {
        return gameRoundRepository.deleteByGameId(gameId);
    }

}
