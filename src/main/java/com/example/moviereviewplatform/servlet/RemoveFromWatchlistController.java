package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.WatchlistService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/watchlist/remove")
public class RemoveFromWatchlistController extends HttpServlet {

    private final WatchlistService watchlistService = WatchlistService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(JspHelper.getPath("user")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем ID пользователя из сессии
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        // Для теста, пока пользователь всегда = 1
        userId = 1;

        // Проверяем, авторизован ли пользователь
        if (userId == null) {
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/login"); // Перенаправляем на страницу входа, если пользователь не авторизован
            return;
        }

        String movieIdParam = req.getParameter("movie_id");

        // Проверка, если movieId не пустое
        if (movieIdParam == null || movieIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie ID is missing");
            return;
        }

        int movieId;
        try {
            movieId = Integer.parseInt(movieIdParam); // Преобразуем строку в целое число
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID format");
            return;
        }

        // Получаем параметр типа списка (например, "watching" или "watched")
        String listType = req.getParameter("list_type");

        // Проверка, если listType пустое
        if (listType == null || listType.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/user");
        }

        try {
            // Удаляем фильм из watchlist
            boolean isDeleted = watchlistService.removeMovieFromWatchlist(userId, movieId, listType);

            // Если фильм был удален, перенаправляем пользователя на страницу профиля
            if (isDeleted) {
                resp.sendRedirect(req.getContextPath() + "/user"); // Перенаправление на профиль

            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found in the watchlist");
            }

        } catch (IllegalArgumentException e) {
            // Логируем ошибку и показываем сообщение пользователю
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
