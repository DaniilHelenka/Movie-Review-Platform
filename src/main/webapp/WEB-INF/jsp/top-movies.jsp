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
        /* Общие стили для страницы */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        /* Стили для хедера */
        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: left;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 100;
        }

        header a {
            color: #fff;
            text-decoration: none;
            margin-right: 15px;
        }

        header a:hover {
            text-decoration: underline;
        }

        /* Основной контейнер */
        .main-container {
            margin-top: 80px;
            padding: 20px;
            text-align: center;
        }

        /* Заголовок */
        h1 {
            color: #333;
            font-size: 2em;
            margin-bottom: 30px;
        }

        /* Стили для списка фильмов */
        .movie-list {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .movie-item {
            background-color: #fff;
            margin: 10px 0;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
            text-align: left;
        }

        .movie-item h3 {
            margin: 0;
            color: #333;
        }

        .movie-item p {
            color: #555;
            font-size: 14px;
        }

        /* Кнопка для просмотра подробностей */
        .movie-item a {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 14px;
        }

        .movie-item a:hover {
            background-color: #45a049;
        }

        /* Сообщение, если нет фильмов */
        .no-movies {
            font-size: 1.2em;
            color: #888;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<!-- Основной контейнер -->
<div class="main-container">
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
</div>

</body>
</html>
