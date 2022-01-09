package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mark;

    private String model;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    public static Car of(String mark, String model) {
        Car car = new Car();
        car.mark = mark;
        car.model = model;
        return car;
    }

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", engine=" + engine
                + ", drivers=" + drivers
                + '}';
    }
}