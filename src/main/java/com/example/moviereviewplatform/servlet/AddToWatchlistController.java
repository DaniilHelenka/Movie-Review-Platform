package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.dto.WatchlistDto;
import com.example.moviereviewplatform.service.WatchlistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/watchlist/add")
public class AddToWatchlistController extends HttpServlet {
    private final WatchlistService watchlistService = WatchlistService.getInstance();

    private static final String USER = "user";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем ID пользователя из сессии
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);
        Integer userId = user.getId();
        // Проверяем, авторизован ли пользователь
        if (userId == null) {
            resp.sendRedirect("/login"); // Перенаправляем на страницу входа, если пользователь не авторизован
            return;
        }

        // Получаем параметры запроса (например, ID фильма)
        String movieIdStr = req.getParameter("movie_id");
        String listType = req.getParameter("list_type");
        if (movieIdStr == null || movieIdStr.isEmpty() || listType == null || listType.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie ID and List Type are required");
            return;
        }

        try {
            Integer movieId = Integer.parseInt(movieIdStr);

            // Добавляем фильм в список пользователя с указанным типом
            watchlistService.addMovieToWatchlist(userId, movieId, listType);

            // Перенаправляем обратно на страницу профиля
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/user");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID format");
        }
    }
}