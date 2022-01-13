package ru.job4j.cars.model;

import java.util.List;

public class BasicInformation {
    private List<City> cities;
    private List<Model> models;

    public BasicInformation(List<City> cities, List<Model> models) {
        this.cities = cities;
        this.models = models;
    }
}
