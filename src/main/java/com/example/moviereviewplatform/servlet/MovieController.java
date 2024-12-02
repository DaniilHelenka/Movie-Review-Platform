package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.MovieService;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Все фильмы</h1>");
            printWriter.write("<ul>");
            movieService.findAll().forEach(movieDto -> {
                printWriter.write("""
                                <li>
                                  <b>Название:</b> <a href="/MovieReviewPlatform_war_exploded/review?movieId=%d">%s</a> <br>
                                  <b>Жанр:</b> %s <br>
                                  <b>Описание:</b> %s <br>
                                  <b>Постер:</b> <img src="%s" alt="постер фильма" width="200px"/><br>
                                 </li>
                        <hr>
                        """.formatted(
                        movieDto.getId(),
                        movieDto.getName(),
                        movieDto.getGenre(),
                        movieDto.getDescription(),
                        movieDto.getPoster_url()
                ));
            });
            printWriter.write("</ul>");
        }
    }
}
