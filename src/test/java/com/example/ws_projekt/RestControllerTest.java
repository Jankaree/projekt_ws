package com.example.ws_projekt;

import com.example.ws_projekt.Model.CityCoordinate;
import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
import com.example.ws_projekt.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RestControllerTest {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    private final CityCoordinateRepository cityCoordinateRepository;

    @Autowired
    public RestControllerTest(MockMvc mockMvc, UserRepository userRepository , CityCoordinateRepository cityCoordinateRepository){
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.cityCoordinateRepository = cityCoordinateRepository;
    }

    //PLEASE NOTE THAT THIS TEST WILL ONLY WORK BASED ON WHAT CITIES YOU HAVE IN YOUR DATABASE
    //IF YOU FOR EXAMPLE HAVE BERLIN AS A CITY YOU NEED TO CHANGE THE PROVIDED STRING ARRAY
    @Test
    void testest() throws Exception {

        String[] cities = new String[]{"Stockholm", "Seoul", "New-York", "Tokyo", "London", "Sydney", "Paris"};

        for (int i = 0; i < cities.length; i++) {
            Optional<CityCoordinate> optionalCity = cityCoordinateRepository.findByCity(cities[i]);
            assertTrue(optionalCity.isPresent());
        }


    }
}
