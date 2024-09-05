package com.mydaytodo.tutorials.spring.reactive.mongo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydaytodo.tutorials.spring.reactive.mongo.model.Car;
import com.mydaytodo.tutorials.spring.reactive.mongo.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class CarControllerTest {
    private MockMvc mockMvc;

    JacksonTester<Car> carTester;
    private final Car TEST_CAR = Car.builder().name("BMW").build();
    private final Car TEST_CAR_2 = Car.builder().name("MG").build();
    @Mock
    private CarService carService;
    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }
    @Test
    void testGetCars() throws Exception {
        String url = "/car";
        when(carService.getAllCars()).thenReturn(Flux.just(TEST_CAR, TEST_CAR_2));
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void testCreateCar() {

    }
    @Test
    void testGetCarById() {

    }
    @Test
    void testDeleteCar() {

    }
    @Test
    void updateCar() {

    }
}
