package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.service.ImageService;
import com.example.moviereviewplatform.service.ReviewService;
import com.example.moviereviewplatform.service.UserService;
import com.example.moviereviewplatform.service.WatchlistService;
import com.example.moviereviewplatform.util.PropertiesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;
@MultipartConfig
@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final String USER = "user";
    private final UserService userService = UserService.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final WatchlistService watchlistService = WatchlistService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем сессию и пользователя из неё
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        if (user != null) {
            Integer userId = user.getId();

            // Устанавливаем атрибуты для изображения и информации о пользователе
            req.setAttribute("image", imageService.get(user.getImage()));
            req.setAttribute("user", user);
            req.setAttribute("userId", userId);

            // Получаем списки watching и watched
            var watchingList = watchlistService.getUserWatchlistByType(userId, "watching");
            var watchedList = watchlistService.getUserWatchlistByType(userId, "watched");

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
