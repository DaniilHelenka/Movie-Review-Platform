<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.dto.MovieDto" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Все фильмы</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #fff;
            margin: 20px auto;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 800px;
        }
        li img {
            border-radius: 8px;
            display: block;
            margin: 10px 0;
        }
        li b {
            color: #4caf50;
        }
        li a {
            text-decoration: none;
            color: #4caf50;
        }
        li a:hover {
            text-decoration: underline;
        }
        p {
            text-align: center;
            color: #555;
        }
        hr {
            border: none;
            border-top: 1px solid #ddd;
            margin: 15px 0;
        }
        button {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            margin-top: 10px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
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

        <%if (user != null) { %>
        <!-- Форма для добавления в watchlist -->
        <form action="<%= request.getContextPath() + "/watchlist/add" %>" method="post">
            <input type="hidden" name="movie_id" value="<%= movie.getId() %>" />
            <input type="hidden" name="list_type" value="watching" />
            <button type="submit">Добавить в Watchlist</button>
        </form>
        <% } else { %>
        <p>Авторизуйтесь, чтобы добавить фильм в Watchlist.</p>
        <% } %>
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
