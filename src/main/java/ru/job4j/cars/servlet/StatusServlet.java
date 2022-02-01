package ru.job4j.cars.servlet;

import ru.job4j.cars.dto.UserDto;
import ru.job4j.cars.repository.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        Long userId = userDto.getId();
        long id = Long.parseLong(req.getParameter("id"));
        AdRepository.instOf().changeStatus(id, userId);
    }
}
