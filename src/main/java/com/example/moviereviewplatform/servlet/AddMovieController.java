package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@WebServlet("/movies/add")
public class AddMovieController extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher((JspHelper.getPath("addMovie")))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String name = req.getParameter("name");
        String genre = req.getParameter("genre");
        String description = req.getParameter("description");
        String poster = req.getParameter("poster");
        LocalDateTime release_date = LocalDateTime.parse(req.getParameter("release_date"));

        movieService.addMovie(name, genre, description, poster, release_date);

        resp.sendRedirect(req.getContextPath() + "/movies");
    }
}
