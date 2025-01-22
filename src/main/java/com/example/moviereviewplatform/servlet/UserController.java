package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.service.impl.ImageServiceImpl;
import com.example.moviereviewplatform.service.impl.UserServiceImpl;
import com.example.moviereviewplatform.service.impl.WatchlistServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@MultipartConfig
@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final String USER = "user";
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    private final ImageServiceImpl imageServiceImpl = ImageServiceImpl.getInstance();
    private final WatchlistServiceImpl watchlistServiceImpl = WatchlistServiceImpl.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем сессию и пользователя из неё
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        if (user != null) {
            Integer userId = user.getId();

            // Устанавливаем атрибуты для изображения и информации о пользователе
            req.setAttribute("image", imageServiceImpl.get(user.getImage()));
            req.setAttribute("user", user);
            req.setAttribute("userId", userId);

            // Получаем списки watching и watched
            var watchingList = watchlistServiceImpl.getUserWatchlistByType(userId, "watching");
            var watchedList = watchlistServiceImpl.getUserWatchlistByType(userId, "watched");

            // Устанавливаем атрибуты для списка
            req.setAttribute("watchingList", watchingList);
            req.setAttribute("watchedList", watchedList);

            // Перенаправляем на страницу user.jsp
            req.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(req, resp);
        } else {
            // Если пользователь не найден в сессии, редиректим на страницу регистрации
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/registration");
        }
    }
}
