package ru.job4j.cars.servlet;

import ru.job4j.cars.model.User;
import ru.job4j.cars.dto.UserDto;
import ru.job4j.cars.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.getRequestDispatcher("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        User user = UserRepository.instOf().findByPhoneAndPassword(phone, password);
        if (user != null) {
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .build();
            req.getSession().setAttribute("user", userDto);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "Неверный номер телефона или пароль");
            req.getRequestDispatcher("login.jsp")
                    .forward(req, resp);
        }
    }
}
