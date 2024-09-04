package com.mydaytodo.tutorials.spring.reactive.mongo.controller;

import com.mydaytodo.tutorials.spring.reactive.mongo.model.Car;
import com.mydaytodo.tutorials.spring.reactive.mongo.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {
    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping
    public Flux<Car> getCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public Mono<Car> createCar(@RequestBody Car car) {
        return carService.create(car);
    }
    @GetMapping("/{id}")
    public Mono<Car> getCarById(@PathVariable("id") String id) {
        return carService.getCarById(id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCar(@PathVariable String id) {
        return carService.deleteCar(id);
    }

    @PutMapping("/{id}")
    public Mono<Car> updateCar(@PathVariable("id") String id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }
}
