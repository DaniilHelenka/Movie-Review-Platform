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
        req.getRequestDispatcher((JspHelper.getPath("user")))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем ID пользователя из сессии
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        userId = 1;
        // Проверяем, авторизован ли пользователь
        if (userId == null) {
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/login"); // Перенаправляем на страницу входа, если пользователь не авторизован
            return;
        }
        String movieIdParam = req.getParameter("movie_id");
        int movieId = Integer.parseInt(movieIdParam);
        // Получаем параметры запроса (ID фильма и тип списка)





        String listType = req.getParameter("list_type"); // Получаем тип списка, например, "watching" или "watched"



        try {

            boolean isDeleted = watchlistService.removeMovieFromWatchlist(userId, movieId, listType);
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/user");
            if (isDeleted) {
                resp.sendRedirect("/MovieReviewPlatform_war_exploded/user"); // Перенаправляем на профиль после успешного удаления
            }
            } catch (IllegalArgumentException e) {
            // Логируем ошибку и показываем сообщение пользователю
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}