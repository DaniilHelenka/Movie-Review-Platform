package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.impl.RecommendationServiceImpl;
import com.example.moviereviewplatform.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/top-movies")
public class TopMoviesController extends HttpServlet {

    private final RecommendationServiceImpl recommendationServiceImpl = RecommendationServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var topMovies = recommendationServiceImpl.getTopRatedMovies();
        req.setAttribute("topMovies", topMovies);
        req.getRequestDispatcher((JspHelper.getPath("top-movies"))).forward(req, resp);
    }
}
