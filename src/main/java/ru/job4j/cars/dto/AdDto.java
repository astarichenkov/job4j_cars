package ru.job4j.cars.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AdDto {
    private String mark;
    private String model;
    private String description;
    private int mileage;
    private int price;
    private String city;
    private boolean isBroken;
    private Date date;
}
