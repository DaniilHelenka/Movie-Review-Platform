package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.entity.Role;
import com.example.moviereviewplatform.exception.ValidationException;
import com.example.moviereviewplatform.service.UserService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(value = "/registration")
public class RegistrationController extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());

        req.getRequestDispatcher((JspHelper.getPath("registration")))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userDto =  CreateUserDto.builder()
               .name(req.getParameter("name"))
                .image(req.getPart("image"))
               .email(req.getParameter("email"))
               .password(req.getParameter("password"))
               .role(req.getParameter("role"))
               .build();

       try{

           userService.create(userDto);
              resp.sendRedirect("/login");
       }catch (ValidationException exception){
           req.setAttribute("error", exception.getErrors());
           doGet(req, resp);
       }
    }
}
