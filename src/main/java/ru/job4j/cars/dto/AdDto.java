package ru.job4j.cars.dto;

import lombok.Builder;
import lombok.Data;
import ru.job4j.cars.model.Ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class AdDto {
    private Long id;
    private String mark;
    private String model;
    private int year;
    private int power;
    private int mileage;
    private int price;
    private boolean isBroken;
    private boolean isSold;
    private String city;
    private String description;
    private String phone;
    private Date date;

    public static AdDto convertToDto(Ad ad) {
        AdDto adDto = AdDto.builder()
                .id(ad.getId())
                .mark(ad.getCar().getMark().getName())
                .model(ad.getCar().getModel().getName())
                .year(ad.getCar().getYear())
                .power(ad.getCar().getPower())
                .mileage(ad.getCar().getMileage())
                .price(ad.getPrice())
                .isBroken(ad.getCar().isBroken())
                .isSold(ad.isSold())
                .city(ad.getCity().getName())
                .description(ad.getDescription())
                .date(ad.getCreated())
                .phone(ad.getAuthor().getPhone())
                .build();
        return adDto;
    }

    public static List<AdDto> convertListToDto(List<Ad> list) {
        List<AdDto> dtoList = new ArrayList<>();
        list.forEach(ad -> dtoList.add(convertToDto(ad)));
        return dtoList;
    }

}
