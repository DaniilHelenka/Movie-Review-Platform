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
public class MovieServlet extends HttpServlet {
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
                    <b>ID:</b> %d <br>
                    <b>Название:</b> %s <br>
                    <b>Описание:</b> %s <br>
                    <b>Постер:</b> <a href="%s" target="_blank">Ссылка на постер</a>
                </li>
                <hr>
                """.formatted(
                        movieDto.getId(),
                        movieDto.getName(),
                        movieDto.getDescription(),
                        movieDto.getPoster_url()
                ));
            });
            printWriter.write("</ul>");
        }
    }
}
