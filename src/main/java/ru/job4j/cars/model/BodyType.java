package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static BodyType of(String name) {
        BodyType bodyType = new BodyType();
        bodyType.name = name;
        return bodyType;
    }
}