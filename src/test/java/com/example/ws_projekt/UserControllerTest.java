package com.example.ws_projekt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ws_projekt.Repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserRepository userRepository){
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }


    @Test
    void testUserCreationPage() throws Exception {
        mockMvc.perform(get("/user/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("UserCreationPage"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("cities"));
    }


    @Test
    void testUserCreation() throws Exception {
        mockMvc.perform(post("/user/add")
                        .param("username", "testuser")
                        .param("password", "testpass")
                        .param("cityOfOrigin", "1"))
                .andExpect(status().isOk());
    }






}
