package com.mydaytodo.tutorials.spring.reactive.mongo.controller;

import com.mydaytodo.tutorials.spring.reactive.mongo.model.Car;
import com.mydaytodo.tutorials.spring.reactive.mongo.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@WebFluxTest(CarController.class)
public class CarControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebTestClient webTestClient;
    private static final String TEST_ID = "ISRD_1221";

    @MockBean
    private CarService carService;
    private final Car TEST_CAR = Car.builder().name("BMW").build();
    private final Car TEST_CAR_2 = Car.builder().name("MG").build();

    @BeforeEach
    void setup() {
    }
    @Test
    void testCreateCar() {
        given(carService.create(any())).willReturn(Mono.just(TEST_CAR));
        webTestClient.post().uri("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(TEST_CAR)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Car.class).isEqualTo(TEST_CAR);
    }
    @Test
    void testGetCarById() {
        given(carService.getCarById(any())).willReturn(Mono.just(TEST_CAR));
        webTestClient.get().uri("/car/"+TEST_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class).isEqualTo(TEST_CAR);
    }
    @Test
    void testDeleteCar() {
        given(carService.getCarById(any())).willReturn(Mono.just(TEST_CAR));
        webTestClient.delete().uri("/car/"+TEST_ID)
                        .exchange()
                    .expectStatus().isOk();
    }
    @Test
    void updateCar() {
        given(carService.updateCar(any(), any())).willReturn(Mono.just(TEST_CAR));
        webTestClient.put().uri("/car/"+TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(TEST_CAR)
                .exchange()
                .expectStatus().isNoContent();
    }
}
