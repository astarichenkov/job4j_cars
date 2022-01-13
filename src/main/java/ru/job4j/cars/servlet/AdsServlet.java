package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AdsServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Item item = GSON.fromJson(req.getReader(), Item.class);
//        item.setCreated(new Timestamp(System.currentTimeMillis()));
//        User user = (User) req.getSession().getAttribute("user");
//        item.setUser(user);
//        HbmStore.instOf().add(item);
//
//        resp.setContentType("application/json; charset=utf-8");
//        OutputStream output = resp.getOutputStream();
//        String json = GSON.toJson(item);
//        output.write(json.getBytes(StandardCharsets.UTF_8));
//        output.flush();
//        output.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Ad> items = AdRepository.instOf().findAllWithPhotos();
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(items);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
