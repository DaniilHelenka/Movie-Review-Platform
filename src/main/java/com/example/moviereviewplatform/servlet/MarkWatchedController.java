package com.example.moviereviewplatform.servlet;


import com.example.moviereviewplatform.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/watchlist/markWatched")
public class MarkWatchedController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из формы
        String movieIdParam = request.getParameter("movie_id");
        String listType = request.getParameter("list_type");

        if (movieIdParam != null && !movieIdParam.isEmpty() && listType != null && !listType.isEmpty()) {
            try {
                int movieId = Integer.parseInt(movieIdParam);

                // Логика перемещения фильма из watching в watched
                // Например, через сервис
                MovieService movieService = new MovieService();
                movieService.moveToWatched(movieId);

                // Перенаправляем пользователя обратно в личный кабинет или на страницу фильмов
                response.sendRedirect(request.getContextPath() + "/user"); // Укажите путь, куда хотите отправить
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат ID фильма");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный параметр");
        }
    }
}