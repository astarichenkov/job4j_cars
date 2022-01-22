package ru.job4j.cars.servlet;

import ru.job4j.cars.model.AdRepository;
import ru.job4j.cars.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        AdRepository.instOf().add(User.of(name, phone, password));
        req.setAttribute("register", "Регистрация завершена");
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }
}