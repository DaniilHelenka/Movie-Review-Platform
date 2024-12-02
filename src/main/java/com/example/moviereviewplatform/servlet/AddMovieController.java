package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.MovieService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@WebServlet("/movies/add")
public class AddMovieController extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var writer = resp.getWriter()) {
            writer.write("""
                    <h1>Добавить фильм</h1>
                        <form action="/movies/add" method="POST">
                                   <input type="text" name="name" placeholder="Название фильма">
                                   <input type="text" name="genre" placeholder="Жанр">
                                   <input type="text" name="description" placeholder="Описание">
                                   <input type="text" name="poster_url" placeholder="Ссылка на постер">
                                   <input type="datetime-local" name="release_date">
                                   <button type="submit">Добавить фильм</button>
                               </form>
                    """);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("AddMovieController.doPost");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String name = req.getParameter("name");
        String genre = req.getParameter("genre");
        String description = req.getParameter("description");
        String poster_url = req.getParameter("poster_url");
        LocalDateTime release_date = LocalDateTime.parse(req.getParameter("release_date"));
        System.out.println("AddMovieController.doPost.addMovie");
        movieService.addMovie(name, genre, description, poster_url, release_date);

        resp.sendRedirect("/movies");
    }
}
