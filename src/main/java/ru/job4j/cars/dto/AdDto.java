package ru.job4j.cars.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AdDto {
    private String mark;
    private String model;
    private int year;
    private int power;
    private int mileage;
    private int price;
    private boolean isBroken;
    private String city;
    private String description;
    private Date date;
}
