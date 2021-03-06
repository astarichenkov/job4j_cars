package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.model.BodyType;
import ru.job4j.cars.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bodies")
public class BodiesServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BodyType> list = CarRepository.instOf().getAllBodytypes();
        String json = GSON.toJson(list);
        req.setAttribute("json", json);
        req.getRequestDispatcher("/writer")
                .forward(req, resp);
    }
}
