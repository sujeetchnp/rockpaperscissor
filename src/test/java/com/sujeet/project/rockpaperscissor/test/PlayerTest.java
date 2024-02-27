package com.sujeet.project.rockpaperscissor.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sujeet.project.rockpaperscissor.model.LoginRequest;
import com.sujeet.project.rockpaperscissor.model.RegisterPlayerRequest;
import com.sujeet.project.rockpaperscissor.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testRegisterAndLogin() throws Exception {
//
//        RegisterPlayerRequest registerRequest = new RegisterPlayerRequest("sujeet", "admin@123", "sujeet@gmail.com", "Manchester", "USA");
//
//        assert registerRequest.getPassword() != null : "Password is null";
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/player/register")
//                .content(asJsonString(registerRequest))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        LoginRequest loginRequest = new LoginRequest("sujeet", "admin@123");
//
//        assert loginRequest.getPassword() != null : "Password is null";
//
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/player/login")
//                .content(asJsonString(loginRequest))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(matchesPattern("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$")));
//
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Test
    public void testRegisterAndLoginWorking() throws Exception {
//        Register a new player

        RegisterPlayerRequest registerRequest = new RegisterPlayerRequest();
        registerRequest.setPlayerName("sujeet");
        registerRequest.setPassword("admin@123");
        registerRequest.setEmail("sujeet@gmail.com");
        registerRequest.setCity("Manchester");
        registerRequest.setCountry("USA");

        mockMvc.perform(post("/player/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Player registered successfully"));

//         Login with registered player

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPlayerName("sujeet");
        loginRequest.setPassword("admin@123");

        mockMvc.perform(post("/player/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$")));

    }

}







/*

// Test from repo layer
    @Test
    public void testPlayerList() throws Exception {
        List<PlayerEntity> playerEntityList = playerRepository.findAll();
        printPlayerList(playerEntityList);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerName("sujeet");
        playerEntity.setUserRole(Role.PLAYER);
        playerEntity.setCity("Manchester");
        playerEntity.setRegisteredDate(new Date());
        playerEntity.setCountry("USA");
        playerEntity.setEmail("sujeet@gmail.com");
        playerRepository.save(playerEntity);

        playerEntityList = playerRepository.findAll();
        printPlayerList(playerEntityList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/player/list");
        mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    private void printPlayerList(List<PlayerEntity> playerEntityList) {
        System.out.println("Size of playerEntityList : " + playerEntityList.size());
        for (PlayerEntity entity : playerEntityList) {
            System.out.println(entity.getPlayerName());
        }

    }
 */
