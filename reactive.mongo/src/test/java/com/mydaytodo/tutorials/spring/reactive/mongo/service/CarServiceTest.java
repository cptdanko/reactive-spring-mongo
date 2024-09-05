package com.mydaytodo.tutorials.spring.reactive.mongo.service;


import com.mydaytodo.tutorials.spring.reactive.mongo.model.Car;
import com.mydaytodo.tutorials.spring.reactive.mongo.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private final Car TEST_CAR = Car.builder().name("BMW").build();
    private final Car TEST_CAR_2 = Car.builder().name("MG").build();
    private static final String TEST_ID = "ISRD_1221";
    private static final String TEST_NAME = "i30";

    @BeforeEach
    public void setup() {
    }
    @Test
    public void testGetCar() {
        when(carRepository.findById(anyString())).thenReturn(Mono.just(Car.builder().name("BMW").build()));
        Mono<Car> carByIdRes = carService.getCarById(TEST_ID);
        StepVerifier.create(carByIdRes)
                .expectNextMatches(car -> car.getName().equals("BMW"))
                .verifyComplete();
    }
    @Test
    public void testGetCarByName() {
        when(carRepository.findByName(anyString())).thenReturn(Mono.just(Car.builder().name("i30").build()));
        Mono<Car> carByIdRes = carService.getCarByName(TEST_NAME);
        StepVerifier.create(carByIdRes)
                .expectNextMatches(car -> car.getName().equals(TEST_NAME))
                .verifyComplete();
    }
    @Test
    public void testCreateCar() {
        when(carRepository.save(any())).thenReturn(Mono.just(TEST_CAR));
        Mono<Car> createCar = carService.create(TEST_CAR);
        StepVerifier.create(createCar)
                .expectNextMatches(car -> car.getName().equals(TEST_CAR.getName()))
                .verifyComplete();
    }

    @Test
    public void testDeleteCar() {
        when(carRepository.deleteById(anyString())).thenReturn(Mono.empty());
        Mono<Void> delRet = carService.deleteCar(TEST_ID);
        StepVerifier.create(delRet)
                .expectComplete()
                .verify();
    }

    @Test
    public void testUpdateCar() {
        when(carRepository.findById(anyString())).thenReturn(Mono.just(TEST_CAR));
        when(carRepository.save(any())).thenReturn(Mono.just(TEST_CAR_2));
        Mono<Car> savedCar = carService.updateCar(TEST_ID, TEST_CAR_2);
        StepVerifier.create(savedCar)
                .expectNextMatches(car -> car.getName().equals(TEST_CAR_2.getName()))
                .verifyComplete();
    }
    @Test
    public void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(Flux.just(TEST_CAR, TEST_CAR_2));
        Flux<Car> carStream = carService.getAllCars();
        StepVerifier.create(carStream)
                .expectNext(TEST_CAR)
                .expectNext(TEST_CAR_2)
                .verifyComplete();
    }
}