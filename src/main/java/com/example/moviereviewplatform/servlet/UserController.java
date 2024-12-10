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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(req, resp);

        // Получение ID пользователя из сессии или параметра
        //Integer userId = (Integer) req.getSession().getAttribute("user_id");
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);




        if (user != null) {
            Integer userId = user.getId();
            req.setAttribute("image", imageService.get(user.getImage()));
            req.setAttribute("user", user);
            req.setAttribute("userId", userId);
            var watchingList = watchlistService.getUserWatchlistByType(userId, "watching");
            var watchedList = watchlistService.getUserWatchlistByType(userId, "watched");
            req.setAttribute("watchingList", watchingList);
            req.setAttribute("watchedList", watchedList);

            req.getRequestDispatcher("/MovieReviewPlatform_war_exploded/user.jsp");
            resp.getWriter().write("User ID: " + userId);
        } else {
            resp.sendRedirect("/MovieReviewPlatform_war_exploded/registration");
        }
    }
}
