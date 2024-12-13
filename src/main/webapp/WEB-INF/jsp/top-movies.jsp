<%@ page import="com.example.moviereviewplatform.entity.Movies" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Топ 10 фильмов</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f5f3;
            color: #333;
        }
        h1 {
            text-align: center;
            color: #2d3436;
            margin-top: 20px;
            font-size: 2em;
        }
        .movie-list {
            list-style-type: none;
            padding: 0;
            margin: 0 auto;
            max-width: 900px;
        }
        .movie-item {
            background-color: #ffffff;
            margin: 20px auto;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
            transition: transform 0.2s ease;
        }
        .movie-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        .movie-item h3 {
            margin: 0;
            color: #0984e3;
        }
        .movie-item p {
            margin: 10px 0 0;
            color: #636e72;
        }
        .movie-item a {
            text-decoration: none;
            color: #ffffff;
            background-color: #0984e3;
            padding: 10px 20px;
            border-radius: 20px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        .movie-item a:hover {
            background-color: #74b9ff;
        }
        .no-movies {
            text-align: center;
            color: #636e72;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<h1>Топ 10 фильмов</h1>
<ul class="movie-list">
    <%
        List<Movies> topMovies = (List<Movies>) request.getAttribute("topMovies");
        if (topMovies != null && !topMovies.isEmpty()) {
            for (Movies movie : topMovies) {
    %>
    <li class="movie-item">
        <div>
            <h3><%= movie.getName() %></h3>
            <p><strong>Жанр:</strong> <%= movie.getGenre() %></p>
        </div>
        <a href="<%= request.getContextPath() + "/review?movieId=" + movie.getId() %>">Подробнее</a>
    </li>
    <%
        }
    } else {
    %>
    <p class="no-movies">Нет данных о фильмах.</p>
    <% } %>
</ul>

</body>
</html>
