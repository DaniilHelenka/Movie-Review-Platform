package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.ReviewService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
    private  final ReviewService reviewService = ReviewService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var movieIdParam = req.getParameter("movieId");
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            if (movieIdParam == null || movieIdParam.isEmpty()) {
                // Если параметр не передан или пуст
                printWriter.write("<h1>Ошибка</h1>");
                printWriter.write("<p>Идентификатор фильма не указан.</p>");
                return;
            }

            try {
                var movieId = Integer.valueOf(movieIdParam); // Преобразуем строку в Integer
                System.out.println(movieId);
                printWriter.write("<h1>Фильм</h1>");
                printWriter.write("<ul>");

                reviewService.findById(movieId).forEach(
                        review -> {
                            // Если отзыв найден
                            printWriter.write(
                                    """
                                    <li>ID: %d</li>
                                    <li>Описание: %s</li>
                                    <li>Рейтинг: %d</li>
                                    <li>ID фильма: %d</li>
                                    """.formatted(
                                            review.getId(),
                                            review.getComment(),
                                            review.getMovieId(),
                                            review.getRating()
                                    )
                            );
                            printWriter.write("</ul>");
                        }
                );
            } catch (NumberFormatException e) {
                // Если передан некорректный параметр
                printWriter.write("<h1>Ошибка</h1>");
                printWriter.write("<p>Некорректный идентификатор фильма.</p>");
            }
        }
    }
}
