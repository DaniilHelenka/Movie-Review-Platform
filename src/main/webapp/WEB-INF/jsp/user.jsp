<%@ page import="com.example.moviereviewplatform.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.dto.WatchlistDto" %>
<%@ page import="com.example.moviereviewplatform.entity.Role" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f6f5f3;
            margin: 0;
            padding: 0;
            color: #333;
        }
        header {
            background-color: #0984e3;
            color: white;
            padding: 15px;
            text-align: center;
            font-size: 24px;
        }
        .profile-container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .profile-container h1 {
            font-size: 2em;
            color: #2d3436;
            margin-bottom: 20px;
            text-align: center;
        }
        .profile-info img {
            display: block;
            margin: 0 auto 20px;
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
        }
        .profile-info p {
            margin: 10px 0;
            font-size: 1.1em;
            color: #636e72;
        }
        .watchlist-container h2 {
            font-size: 1.5em;
            color: #2d3436;
            margin-top: 30px;
        }
        .watchlist-container ul {
            list-style: none;
            padding: 0;
        }
        .watchlist-container li {
            margin: 15px 0;
            padding: 15px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 8px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .watchlist-container button {
            background-color: #d63031;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }
        .watchlist-container button:hover {
            background-color: #ff7675;
        }
        .profile-buttons a, .profile-buttons button {
            background-color: #0984e3;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            margin: 10px 5px;
            display: inline-block;
        }
        .profile-buttons a:hover, .profile-buttons button:hover {
            background-color: #74b9ff;
        }
        .error-message {
            text-align: center;
            font-size: 1.2em;
            color: #d63031;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<header>
    MovieReviewPlatform
</header>

<div class="profile-container">
    <%
        UserDto user = (UserDto) session.getAttribute("user");
        if (user != null) {
    %>
    <h1>Профиль пользователя</h1>
    <div class="profile-info">
        <img src="<%= request.getContextPath() %>/images/<%= user.getImage() %>" alt="User image">
        <p><strong>ID:</strong> <%= user.getId() %></p>
        <p><strong>Имя:</strong> <%= user.getName() %></p>
        <p><strong>Email:</strong> <%= user.getEmail() %></p>
    </div>

    <div class="profile-buttons">
        <form action="<%= request.getContextPath() %>/logout" method="post" style="display:inline;">
            <button type="submit">Выйти</button>
        </form>
        <a href="<%= request.getContextPath() %>/movies">Все фильмы</a>

        <%
            // Проверяем роль пользователя
            if (user.getRole() == Role.ADMIN) {
        %>
        <a href="<%= request.getContextPath() %>/movies/add">Добавить фильм</a>
        <a href="<%= request.getContextPath() %>/movies/delete">Удалить фильм</a>
        <%
            }
        %>
    </div>

    <div class="watchlist-container">
        <h2>Буду смотреть</h2>
        <ul>
            <%
                List<WatchlistDto> watchingList = (List<WatchlistDto>) request.getAttribute("watchingList");
                if (watchingList != null && !watchingList.isEmpty()) {
                    for (WatchlistDto item : watchingList) {
            %>
            <li>
                <span><strong>Название:</strong> <%= item.getMovieName() %></span>
                <form action="<%= request.getContextPath() + "/watchlist/markWatched" %>" method="post">
                    <input type="hidden" name="movie_id" value="<%= item.getMovieId() %>">
                    <input type="hidden" name="list_type" value="watching">
                    <button type="submit">Посмотрел</button>
                </form>
                <form action="<%= request.getContextPath() %>/watchlist/remove" method="post">
                    <input type="hidden" name="movie_id" value="<%= item.getMovieId() %>">
                    <input type="hidden" name="list_type" value="watching">
                    <button type="submit">Удалить</button>
                </form>
            </li>
            <%
                }
            } else {
            %>
            <p>Список пуст.</p>
            <%
                }
            %>
        </ul>

        <h2>Посмотрено</h2>
        <ul>
            <%
                List<WatchlistDto> watchedList = (List<WatchlistDto>) request.getAttribute("watchedList");
                if (watchedList != null && !watchedList.isEmpty()) {
                    for (WatchlistDto item : watchedList) {
            %>
            <li>
                <span><strong>Название:</strong> <%= item.getMovieName() %></span>
                <form action="<%= request.getContextPath() %>/watchlist/remove" method="post">
                    <input type="hidden" name="movie_id" value="<%= item.getMovieId() %>">
                    <input type="hidden" name="list_type" value="watched">
                    <button type="submit">Удалить</button>
                </form>
            </li>
            <%
                }
            } else {
            %>
            <p>Список пуст.</p>
            <%
                }
            %>
        </ul>
    </div>
    <% } else { %>
    <p class="error-message">Пользователь не найден. Пожалуйста, войдите в систему.</p>
    <% } %>
</div>

</body>
</html>
