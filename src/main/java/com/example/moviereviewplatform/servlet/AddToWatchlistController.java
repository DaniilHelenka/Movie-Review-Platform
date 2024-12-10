package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.WatchlistService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/watchlist/add")
public class AddToWatchlistController extends HttpServlet {
    private final WatchlistService watchlistService = WatchlistService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = (int) req.getSession().getAttribute("user_id");
        int movieId = Integer.parseInt(req.getParameter("movie_id"));
        String listType = req.getParameter("list_type"); // "watching" или "watched"

        watchlistService.addMovieToWatchlist(userId, movieId, listType);
        resp.sendRedirect(req.getContextPath() + "/user");
    }
}