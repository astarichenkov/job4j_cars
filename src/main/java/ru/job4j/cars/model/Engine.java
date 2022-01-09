package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int power;

    public Engine() {
    }

    public static Engine of(int power) {
        Engine engine = new Engine();
        engine.setPower(power);
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", power=" + power
                + '}';
    }
}