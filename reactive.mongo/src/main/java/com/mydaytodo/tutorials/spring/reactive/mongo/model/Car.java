package com.mydaytodo.tutorials.spring.reactive.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cars")
@Builder
public class Car {
    @Id
    private String id;
    private String name;
}
