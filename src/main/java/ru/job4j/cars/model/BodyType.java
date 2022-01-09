package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "bodytypes")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public BodyType() {
    }

    public static BodyType of(String name) {
        BodyType bodyType = new BodyType();
        bodyType.name = name;
        return bodyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BodyType{"
                + "id=" + id
                + ", name='" + name
                + '}';
    }
}