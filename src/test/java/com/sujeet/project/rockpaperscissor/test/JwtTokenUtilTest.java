package com.sujeet.project.rockpaperscissor.test;

import com.sujeet.project.rockpaperscissor.model.Player;
import com.sujeet.project.rockpaperscissor.model.Role;
import com.sujeet.project.rockpaperscissor.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenUtilTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testGenerateToken(){
        Player player = new Player();
        player.setPlayerName("sujeet");
        player.setEmail("sujeet@gmail.com");
        player.setUserRole(Role.PLAYER);
        String token = jwtTokenUtil.generateToken(player);
        Assert.assertNotNull("Token should not be null", token);

        Claims cl = jwtTokenUtil.getAllClaimsFromToken(token);
        String playerName = cl.get("playerName").toString();
        String email = cl.get("email").toString();
        String role = cl.get("userRole").toString();

        Assert.assertEquals("sujeet", playerName);
        Assert.assertEquals("sujeet@gmail.com", email);
        Assert.assertEquals(Role.PLAYER.toString(), role);

    }

    @Test(expected = MalformedJwtException.class)
    public void testInvalidToken(){

        String token = "abcdfhdslfhoisddhljsdf";
        Claims cl = jwtTokenUtil.getAllClaimsFromToken(token);

    }
}
