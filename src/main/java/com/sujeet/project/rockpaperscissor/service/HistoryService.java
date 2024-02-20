package com.sujeet.project.rockpaperscissor.service;

import com.sujeet.project.rockpaperscissor.model.GameHistoryModel;

import java.util.List;

public interface HistoryService {
    List<GameHistoryModel> getAllHistory(String playerName);
    int deleteHistory(String playerName);

    int deleteRoundHistoryByGameId(Integer gameId);
}
