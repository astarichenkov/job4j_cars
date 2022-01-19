package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cars")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;
    private int mileage;
    private int power;
    private boolean isBroken;


//    @ManyToOne
//    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
//    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "bodytype_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;

    @ManyToOne
    @JoinColumn(name = "mark_id", foreignKey = @ForeignKey(name = "MAARK_ID_FK"))
    private Mark mark;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    public static Car of(Mark mark, Model model, BodyType bodyType, int year, int power, int mileage, boolean isBroken) {
        Car car = new Car();
        car.mark = mark;
        car.model = model;
        car.bodyType = bodyType;
        car.year = year;
        car.power = power;
        car.mileage = mileage;
        car.isBroken = isBroken;
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


    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", year=" + year
                + ", mileage=" + mileage
                + ", isBroken=" + isBroken
                + ", power =" + power
                + ", bodyType=" + bodyType
                + ", model=" + model
                + ", mark=" + mark
                + ", drivers=" + drivers
                + '}';
    }
}