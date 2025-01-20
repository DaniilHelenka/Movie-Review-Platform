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
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0 auto;
            max-width: 900px;
        }
        li {
            background-color: #ffffff;
            margin: 20px auto;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease;
        }
        li:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        li img {
            border-radius: 10px;
            display: block;
            margin: 15px 0;
            max-width: 200px;
            height: auto;
        }
        li b {
            color: #0984e3;
        }
        li a {
            text-decoration: none;
            color: #0984e3;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        li a:hover {
            color: #74b9ff;
        }
        p {
            text-align: center;
            color: #636e72;
        }
        hr {
            border: none;
            border-top: 1px solid #dfe6e9;
            margin: 20px 0;
        }
        button {
            background-color: #0984e3;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 20px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #74b9ff;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            display: inline-block;
            margin: 0 5px;
            padding: 10px 15px;
            background-color: #0984e3;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .pagination a:hover {
            background-color: #74b9ff;
        }
        .pagination span {
            margin: 0 5px;
            padding: 10px 15px;
            background-color: #dfe6e9;
            color: #636e72;
            border-radius: 5px;
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
        <b>Постер:</b> <img src="<%= request.getContextPath() %>/images/<%=movie.getPoster_url()%>" alt="постер фильма" /><br>

        <% if (user != null) { %>
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

<!-- Пагинация -->
<div class="pagination">
    <%
        int currentPage = (int) request.getAttribute("currentPage");
        int totalPages = (int) request.getAttribute("totalPages");
        int pageSize = (int) request.getAttribute("pageSize");

        if (currentPage > 1) {
    %>
    <a href="?page=<%= currentPage - 1 %>&size=<%= pageSize %>">Предыдущая</a>
    <% } else { %>
    <span>Предыдущая</span>
    <% } %>

    <% for (int i = 1; i <= totalPages; i++) { %>
    <a href="?page=<%= i %>&size=<%= pageSize %>"><%= i %></a>
    <% } %>

    <% if (currentPage < totalPages) { %>
    <a href="?page=<%= currentPage + 1 %>&size=<%= pageSize %>">Следующая</a>
    <% } else { %>
    <span>Следующая</span>
    <% } %>
</div>

</body>
</html>
