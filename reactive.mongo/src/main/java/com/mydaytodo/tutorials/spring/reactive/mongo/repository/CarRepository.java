package com.mydaytodo.tutorials.spring.reactive.mongo.repository;

import com.mongodb.lang.NonNull;
import com.mydaytodo.tutorials.spring.reactive.mongo.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {

    Mono<Car> findByName(String id);
}
