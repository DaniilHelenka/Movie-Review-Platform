package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.service.impl.MovieServiceImpl;
import com.example.moviereviewplatform.service.impl.ReviewServiceImpl;
import com.example.moviereviewplatform.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
    private final ReviewServiceImpl reviewServiceImpl = ReviewServiceImpl.getInstance();
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    private final MovieServiceImpl movieServiceImpl = MovieServiceImpl.getInstance();
    private static final String USER = "user";

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
            List<ReviewDto> reviews = reviewServiceImpl.findAllByMovieId(movieId);

            Optional<MovieDto> movieOptional = movieServiceImpl.findById(movieId);
            if (movieOptional.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Фильм с указанным идентификатором не найден.");
                return;
            }
            MovieDto movie = movieOptional.get();
            req.setAttribute("movieName", movie.getName());
            req.setAttribute("poster", movie.getPoster_url());

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
        // Получаем usera
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);
        //userService.getId((Integer) req.getSession().getAttribute("user_id"));
        Integer user_id = user.getId();
        // Integer user_id = Integer.valueOf(req.getParameter("user_id"));
        Integer movie_id = Integer.parseInt(req.getParameter("movie_id"));
        Integer rating = Integer.parseInt(req.getParameter("rating"));
        String comments = req.getParameter("comments");
        LocalDateTime created_at = LocalDateTime.now();

        reviewServiceImpl.addReview(user_id, movie_id, rating, comments);
        resp.sendRedirect("/MovieReviewPlatform_war_exploded/review?movieId=" + movie_id);
    }
    @SneakyThrows
    private void postReview(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
    }
}
