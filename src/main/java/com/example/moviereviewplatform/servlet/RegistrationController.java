package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.service.UserService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("USER", "ADMIN"));

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       var userDto =  CreateUserDto.builder()
               .name(req.getParameter("name"))
               .email(req.getParameter("email"))
               .password(req.getParameter("password"))
               .build();

       userService.create(userDto);
       resp.sendRedirect("/login");
    }
}
