package com.sujeet.project.rockpaperscissor.util;

import com.sujeet.project.rockpaperscissor.model.Player;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;


    // this method extracts the claims(payload) from a given JWT token. It uses the secret to parse and validate the token's signature
    // The Claims object contains all the information(claims) stored in the token, such as the subject, issuer, expiration time, etc
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // this method generates token based on claims, subject, sets expiration of token
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // this method generates a token for a "Player" object.
    public String generateToken(Player player) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("playerName", player.getPlayerName());
        claims.put("email", player.getEmail());
        claims.put("userRole", player.getUserRole());

        return doGenerateToken(claims, player.getPlayerName());  // calls doGenerateToken with these claims and player's name as the subject
    }
}
