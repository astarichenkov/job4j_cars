package ru.job4j.cars.model;

import java.util.List;

public class BasicInformation {
    private List<Model> models;
    private List<Mark> marks;
    private List<BodyType> bodies;
    private List<City> cities;

    public BasicInformation(List<Model> models, List<Mark> marks, List<BodyType> bodies, List<City> cities) {
        this.models = models;
        this.marks = marks;
        this.bodies = bodies;
        this.cities = cities;
    }
}
