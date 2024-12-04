package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/movies/delete")
public class DeleteMovieController extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher((JspHelper.getPath("deleteMovie")))
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID фильма не указан");
            return;
        }
        try {
            Integer movieId = Integer.valueOf(idParam);
            boolean isDeleted = movieService.deleteMovie(movieId);

            if (isDeleted) {
                resp.sendRedirect("movies"); // Успешно удален, редирект на список фильмов
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Фильм с таким ID не найден");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный ID");
        }
    }
}