package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.AdRepository;
import ru.job4j.cars.model.BasicInformation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BasicServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        City city = GSON.fromJson(req.getReader(), City.class);
//        resp.setContentType("application/json; charset=utf-8");
//        OutputStream output = resp.getOutputStream();
//        String json = GSON.toJson(city);
//        output.write(json.getBytes(StandardCharsets.UTF_8));
//        output.flush();
//        output.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        List<City> cities = AdRepository.instOf().getAllCities();
//        List<Model> models = AdRepository.instOf().getAllModels();
//        List<Mark> marks = AdRepository.instOf().getAllMarks();
//        List<BodyType> bodytypes = AdRepository.instOf().getAllBodytypes();
//        BasicInformation basicInformation = new BasicInformation(models, marks, bodytypes, cities);
        BasicInformation bs = AdRepository.instOf().getBasicInformation();
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(bs);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}