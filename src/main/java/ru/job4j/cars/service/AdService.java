//package ru.job4j.cars.service;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//import ru.job4j.cars.model.*;
//import ru.job4j.cars.repository.AdRepository;
//import ru.job4j.cars.repository.CarRepository;
//import ru.job4j.cars.repository.UserRepository;
//
//import java.util.List;
//
//public class AdService {
//
//    private static final Gson GSON = new GsonBuilder().create();
//
//
//    public static AdDto adToDto(Ad ad) {
//        AdDto dto = AdDto.builder()
//                .mark(ad.getCar().getMark().getName())
//                .model(ad.getCar().getModel().getName())
//                .description(ad.getDescription())
//                .mileage(ad.getCar().getMileage())
//                .price(ad.getPrice())
//                .isBroken(ad.getCar().isBroken())
//                .date(ad.getCreated())
//                .build();
//        return dto;
//    }
//
////    public static AdDto findAdById(Long id) {
////        List<Ad> list = AdRepository.instOf().findAdById(Long.valueOf(id));
////    }
//
//
//    public static Ad createAd(JsonObject jobj, String username) {
//        String description = jobj.get("description").getAsString();
//
//        int power = jobj.get("power").getAsInt();
//        int mileage = jobj.get("mileage").getAsInt();
//        int year = jobj.get("year").getAsInt();
//        boolean isBroken = jobj.get("isBroken").getAsBoolean();
//        int price = jobj.get("price").getAsInt();
//
//        Mark mark = CarRepository.instOf().findMarkById(jobj.get("mark").getAsInt());
//        Model model = CarRepository.instOf().findModelById(jobj.get("model").getAsInt());
//        BodyType body = CarRepository.instOf().findBodyById(jobj.get("body").getAsInt());
//        City city = AdRepository.instOf().findCityById(jobj.get("city").getAsLong());
//
//        Car car = Car.of(mark, model, body, year, power, mileage, isBroken);
//        CarRepository.instOf().add(car);
//
////        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
//        User user = UserRepository.instOf().findByName(username);
//
//        Ad ad = Ad.of(car, description, city, user, price);
//        AdRepository.instOf().add(ad);
//        return ad;
//    }
//}
