package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static Mark of(String name) {
        Mark mark = new Mark();
        mark.name = name;
        return mark;
    }
}

