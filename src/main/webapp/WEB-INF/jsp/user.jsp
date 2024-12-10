<%@ page import="com.example.moviereviewplatform.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.entity.Watchlist" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #eef2f3;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #343a40;
            color: #fff;
            padding: 20px;
            text-align: center;
            font-size: 24px;
        }

        .profile-container {
            background-color: #fff;
            border-radius: 8px;
            margin: 50px auto;
            padding: 30px;
            width: 70%;
            max-width: 700px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }

        .profile-container h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
        }

        .profile-info, .watchlist-container {
            font-size: 18px;
            color: #555;
            margin-bottom: 30px;
        }

        .profile-info p, .watchlist-container p {
            margin: 10px 0;
        }

        .profile-info strong {
            color: #333;
        }

        .watchlist-container h2 {
            font-size: 22px;
            margin-top: 20px;
            color: #333;
        }

        .watchlist-container ul {
            list-style: none;
            padding: 0;
        }

        .watchlist-container li {
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .profile-buttons {
            text-align: center;
        }

        .profile-buttons a, .profile-buttons button {
            background-color: #007bff;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            margin: 10px;
            cursor: pointer;
        }

        .profile-buttons a:hover, .profile-buttons button:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            font-size: 18px;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<header>
    MovieReviewPlatform
</header>

<div class="profile-container">
    <h1>Профиль пользователя</h1>

    <%
        // Получаем объект пользователя из сессии
        UserDto user = (UserDto) session.getAttribute("user");
        if (user != null) {
    %>
    <div class="profile-info">
        <img src="<%= request.getContextPath() %>/images/<%= user.getImage() %>" alt="User image" style="width: 150px; height: auto; border-radius: 50%;">
        <p><strong>ID пользователя:</strong> <%= user.getId() %></p>
        <p><strong>Имя пользователя:</strong> <%= user.getName() %></p>
        <p><strong>Email:</strong> <%= user.getEmail() %></p>
    </div>

    <div class="profile-buttons">
        <form action="/MovieReviewPlatform_war_exploded/logout" method="post" style="display:inline;">
            <button type="submit">Выйти</button>
        </form>
        <a href="movies">Все фильмы</a>
    </div>

    <div class="watchlist-container">
        <h2>Буду смотреть</h2>
        <ul>
            <%
                List<Watchlist> watchingList = (List<Watchlist>) request.getAttribute("watchingList");
                if (watchingList != null && !watchingList.isEmpty()) {
                    for (Watchlist item : watchingList) {
            %>
            <li>
                <b>Название фильма:</b> <%= item.getMovieName() %>
                <form action="<%= request.getContextPath() + "/watchlist/remove" %>" method="post" style="display:inline;">
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
                List<Watchlist> watchedList = (List<Watchlist>) request.getAttribute("watchedList");
                if (watchedList != null && !watchedList.isEmpty()) {
                    for (Watchlist item : watchedList) {
            %>
            <li>
                <b>Название фильма:</b> <%= item.getMovieName() %>
                <form action="<%= request.getContextPath() + "/watchlist/remove" %>" method="post" style="display:inline;">
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
