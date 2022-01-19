package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    public City() {
    }

    public static City of(String name) {
        City city = new City();
        city.name = name;
        return city;
    }

    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
