package com.sujeet.project.rockpaperscissor.service;

import com.sujeet.project.rockpaperscissor.model.LoginRequest;
import com.sujeet.project.rockpaperscissor.model.Player;
import com.sujeet.project.rockpaperscissor.model.RegisterPlayerRequest;

public interface PlayerService {
    Player register(RegisterPlayerRequest registerPlayerRequest);

    String login(LoginRequest loginRequest);

}
