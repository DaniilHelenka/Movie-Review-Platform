package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.service.ReviewService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
    private final ReviewService reviewService = ReviewService.getInstance();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var movieIdParam = req.getParameter("movieId");
        if (movieIdParam == null || movieIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Идентификатор фильма не указан.");
            return;
        }
        try {
            int movieId = Integer.parseInt(movieIdParam);
            // Получаем все отзывы для фильма
            List<ReviewDto> reviews = reviewService.findAll(movieId);

            // Добавляем отзывы в запрос для передачи в JSP
            req.setAttribute("reviews", reviews);
            req.setAttribute("movieId", movieId);

            // Перенаправляем на JSP-страницу для отображения
            req.getRequestDispatcher("/WEB-INF/jsp/reviews.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный идентификатор фильма.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name()); // Устанавливаем кодировку

        Integer user_id = Integer.parseInt(req.getParameter("user_id"));
        Integer movie_id = Integer.parseInt(req.getParameter("movie_id"));
        Integer rating = Integer.parseInt(req.getParameter("rating"));
        String comment = req.getParameter("comment");
        LocalDateTime created_at = LocalDateTime.now();

        reviewService.addReview(user_id, movie_id, rating, comment, created_at);
        resp.sendRedirect("/review?movieId=" + movie_id);
    }
}
