package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.CreateMovieDto;
import com.example.moviereviewplatform.service.impl.MovieServiceImpl;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;


@WebServlet("/movies/add")
@MultipartConfig
public class AddMovieController extends HttpServlet {

    private final MovieServiceImpl movieServiceImpl = MovieServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher((JspHelper.getPath("addMovie")))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var movieDto = CreateMovieDto.builder()
                .name(req.getParameter("name"))
                .genre(req.getParameter("genre"))
                .description(req.getParameter("description"))
                .release_date(LocalDate.parse(req.getParameter("release_date")))
                .poster_url(req.getPart("poster_url"))
                .build();

        movieServiceImpl.addMovie(movieDto);

        resp.sendRedirect("/MovieReviewPlatform_war_exploded/movies");
    }
}
