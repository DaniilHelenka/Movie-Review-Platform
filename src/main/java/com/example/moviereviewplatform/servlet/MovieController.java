package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.service.RecommendationService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/movies")
public class MovieController extends HttpServlet {
    private final MovieService movieService = MovieService.getInstance();
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        var movies = movieService.findAll(); // Получаем список фильмов
        for (MovieDto movie : movies) {
            req.setAttribute("poster", movie.getPoster_url());
        }
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        if (user != null) {
            Integer userIdObj = user.getId();
            int userId = (int) userIdObj;
            req.setAttribute("user_id", userId); // Передаем ID пользователя
        } else {
            req.setAttribute("user_id", null);
        }

        req.setAttribute("movies", movies); // Передаем список в запрос
        req.getRequestDispatcher((JspHelper.getPath("movies")))
                .forward(req, resp);

    }
}

