package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.LOCATE)
public class LocaleController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);

        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : UrlPath.LOGIN;
        // will be better to use header Accept-Language instead of param lang
        resp.sendRedirect(page + "?lang=" + language);
    }
}