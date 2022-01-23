package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.job4j.cars.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ads")
public class AdsServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder();
        while (req.getReader().ready()) {
            sb.append(req.getReader().readLine());
        }
        System.out.println("SBUILDER " + sb.toString());

        JsonObject jobj = new Gson().fromJson(String.valueOf(sb), JsonObject.class);
        String description = jobj.get("description").getAsString();

        int power = jobj.get("power").getAsInt();
        int mileage = jobj.get("mileage").getAsInt();
        int year = jobj.get("year").getAsInt();
        boolean isBroken = jobj.get("isBroken").getAsBoolean();
        int price = jobj.get("price").getAsInt();

        Mark mark = AdRepository.instOf().findMarkById(jobj.get("mark").getAsInt());
        Model model = AdRepository.instOf().findModelById(jobj.get("model").getAsInt());
        BodyType body = AdRepository.instOf().findBodyById(jobj.get("body").getAsInt());
        City city = AdRepository.instOf().findCityById(jobj.get("city").getAsLong());

        Car car = Car.of(mark, model, body, year, power, mileage,  isBroken);
        AdRepository.instOf().add(car);
        System.out.println(car.getId());

        User user = AdRepository.instOf().findUserByName("Anton");

        Ad ad = Ad.of(car, description, city, user, price);
        AdRepository.instOf().add(ad);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Ad> list = AdRepository.instOf().findAll();
        String json = GSON.toJson(list);
        req.setAttribute("json", json);
        req.getRequestDispatcher("/writer")
                .forward(req, resp);
    }
}