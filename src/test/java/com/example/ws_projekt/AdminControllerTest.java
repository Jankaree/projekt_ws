package com.example.ws_projekt;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
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
public class AdminControllerTest {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;



    @Autowired
    public AdminControllerTest(MockMvc mockMvc, UserRepository userRepository){
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;

    }

    @Test
    @WithMockUser(username = "test", roles = "USER")
    void testAdminPage() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminPageWithLogin() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }






}