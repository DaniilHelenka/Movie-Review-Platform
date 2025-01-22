package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.entity.Role;
import com.example.moviereviewplatform.exception.ValidationException;
import com.example.moviereviewplatform.service.impl.UserServiceImpl;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig(location = "F:/work/images")
@WebServlet(value = "/registration")
public class RegistrationController extends HttpServlet {

    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());

        req.getRequestDispatcher((JspHelper.getPath("registration")))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .image(req.getPart("image"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .build();
        try {
            userServiceImpl.create(userDto);
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/login");
        } catch (ValidationException exception) {
            req.setAttribute("error", exception.getErrors());
            doGet(req, resp);
        }
    }
}
