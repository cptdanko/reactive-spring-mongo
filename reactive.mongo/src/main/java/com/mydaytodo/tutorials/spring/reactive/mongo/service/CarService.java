package com.mydaytodo.tutorials.spring.reactive.mongo.service;

import com.mydaytodo.tutorials.spring.reactive.mongo.model.Car;
import com.mydaytodo.tutorials.spring.reactive.mongo.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CarService {

    @Autowired
    private CarRepository carRepository;
    private final Car TEST_CAR = Car.builder().name("BMW").build();
    private final Car TEST_CAR_2 = Car.builder().name("MG").build();

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Flux<Car> getAllCars() {
        log.info("In the final all cars");
        return carRepository.findAll();
    }
    public Mono<Car> getCarById(String id) {
        return carRepository.findById(id);
    }
    public Mono<Car> getCarByName(String name) {
        return carRepository.findByName(name);
    }

    public Mono<Car> create(Car car) {
        return carRepository.save(car);
    }
    public Mono<Void> deleteCar(String id) {
        return carRepository.deleteById(id);
    }
    public Mono<Car> updateCar(String id, Car car) {
        return carRepository.findById(id)
                .flatMap(existingCar -> {
                    existingCar.setName(car.getName());
                    return carRepository.save(existingCar);
                });
    }
}
