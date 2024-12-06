<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.dto.MovieDto" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Все фильмы</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Все фильмы</h1>
<ul>
    <%
        // Получаем список фильмов из атрибута запроса
        List<MovieDto> movies = (List<MovieDto>) request.getAttribute("movies");
        if (movies != null && !movies.isEmpty()) {
            for (MovieDto movie : movies) {
    %>
    <li>
        <b>Название:</b> <a href="<%= request.getContextPath() + "/review?movieId=" + movie.getId() %>"><%= movie.getName() %></a> <br>
        <b>Жанр:</b> <%= movie.getGenre() %> <br>
        <b>Описание:</b> <%= movie.getDescription() %> <br>
        <b>Постер:</b> <img src="<%= movie.getPoster_url() %>" alt="постер фильма" width="200px" /><br>
    </li>
    <hr>
    <%
        }
    } else {
    %>
    <p>Фильмы не найдены.</p>
    <%
        }
    %>
</ul>
</body>
</html>
