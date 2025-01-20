package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.service.ImageService;
import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/movies")
public class MovieController extends HttpServlet {
    private final MovieService movieService = MovieService.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Установка значений по умолчанию для пагинации
        int page = 1;
        int size = 2;

        // Проверка параметров запроса
        String pageParam = req.getParameter("page");
        String sizeParam = req.getParameter("size");

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Лог или игнорирование ошибки
                page = 1;
            }
        }

        if (sizeParam != null && !sizeParam.isEmpty()) {
            try {
                size = Integer.parseInt(sizeParam);
            } catch (NumberFormatException e) {
                // Лог или игнорирование ошибки
                size = 10;
            }
        }

        // Получение фильмов с пагинацией
        List<MovieDto> moviesPaginated = movieService.findAllPaginated(page, size);
        req.setAttribute("movies", moviesPaginated);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        var movies = movieService.findAll();

        int totalMovies = movies.size(); // Общее количество фильмов
        int totalPages = (int) Math.ceil((double) totalMovies / size); // Количество страниц
        req.setAttribute("totalPages", totalPages);

        // Получение данных пользователя
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        if (user != null) {
            req.setAttribute("user_id", user.getId());
        } else {
            req.setAttribute("user_id", null);
        }

        // Обработка изображений (если необходимо)
        for (MovieDto movie : moviesPaginated) {
            imageService.get(movie.getPoster_url());
        }

        // Передача управления на JSP
        req.getRequestDispatcher(JspHelper.getPath("movies"))
                .forward(req, resp);
    }
}
