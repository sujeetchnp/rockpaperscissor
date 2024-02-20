package com.sujeet.project.rockpaperscissor.service;

import com.sujeet.project.rockpaperscissor.entity.PlayerEntity;
import com.sujeet.project.rockpaperscissor.exception.InvalidInputException;
import com.sujeet.project.rockpaperscissor.model.LoginRequest;
import com.sujeet.project.rockpaperscissor.model.Player;
import com.sujeet.project.rockpaperscissor.model.RegisterPlayerRequest;
import com.sujeet.project.rockpaperscissor.model.Role;
import com.sujeet.project.rockpaperscissor.repository.PlayerRepository;
import com.sujeet.project.rockpaperscissor.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Player convertToPlayerModel(PlayerEntity entity){
        Player model = new Player();
        model.setPlayerName(entity.getPlayerName());
        model.setEmail(entity.getEmail());
        model.setCity(entity.getCity());
        model.setCountry(entity.getCountry());
        model.setUserRole(entity.getUserRole());
        return model;
    }
    @Override
    public Player register(RegisterPlayerRequest registerPlayerRequest) {
        PlayerEntity newPlayer =  new PlayerEntity();
        newPlayer.setPlayerName(registerPlayerRequest.getPlayerName());
        newPlayer.setEmail(registerPlayerRequest.getEmail());
        newPlayer.setCity(registerPlayerRequest.getCity());

        newPlayer.setCountry(registerPlayerRequest.getCountry());

        newPlayer.setUserRole(Role.PLAYER);

        String encodedPassword = bCryptPasswordEncoder.encode(registerPlayerRequest.getPassword());

        newPlayer.setPassword(encodedPassword);

        PlayerEntity savedEntity = playerRepository.save(newPlayer);

        return convertToPlayerModel(savedEntity);
    }

    @Override
    @Transactional
    public String login(LoginRequest loginRequest) {
        String playerName = loginRequest.getPlayerName();
        String password = loginRequest.getPassword();

        PlayerEntity playerEntity = playerRepository.findByPlayerName(playerName);

        if (playerEntity == null) {
            throw new InvalidInputException("Invalid username!");
        }

        Boolean isPasswordMatches = bCryptPasswordEncoder.matches(password, playerEntity.getPassword());
        if (!isPasswordMatches) {
            throw new InvalidInputException("Invalid Password!");
        }
        playerRepository.updateLoggedInDate(playerName,new Date());
        return jwtTokenUtil.generateToken(convertToPlayerModel(playerEntity));
    }
}
