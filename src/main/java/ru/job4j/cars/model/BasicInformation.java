package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "basic")
public class BasicInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Model> models;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<Mark> marks;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<BodyType> bodies;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<City> cities;

    public BasicInformation() {
    }

    public static BasicInformation of(Set<Model> models, Set<Mark> marks, Set<BodyType> bodies, Set<City> cities) {
        BasicInformation bs = new BasicInformation();
        bs.models = models;
        bs.marks = marks;
        bs.bodies = bodies;
        bs.cities = cities;
        return bs;
    }

    @Override
    public String toString() {
        return "BasicInformation{"
                + "id=" + id
                + ", models=" + models
                + ", marks=" + marks
                + ", bodies=" + bodies
                + ", cities=" + cities
                + '}';
    }
}
