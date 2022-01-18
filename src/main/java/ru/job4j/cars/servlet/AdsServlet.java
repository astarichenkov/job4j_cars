package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet("/ads")
public class AdsServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (req.getReader().ready()) {
            sb.append(req.getReader().readLine());
        }
        System.out.println("SBUILDER " + sb);
        Ad ad = GSON.fromJson(sb.toString(), Ad.class);
        ad.setCreated(LocalDateTime.now());
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