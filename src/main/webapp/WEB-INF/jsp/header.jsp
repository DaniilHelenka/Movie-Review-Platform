<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieReviewPlatform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .navbar {
            background-color: #4caf50;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }
        .navbar a, .navbar button {
            color: white;
            text-decoration: none;
            margin: 0 10px;
            background-color: #45a049;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }
        .navbar a:hover, .navbar button:hover {
            background-color: #3e8e41;
        }
        .navbar form {
            margin: 0;
            display: inline;
        }
        .navbar h1 {
            margin: 0;
            font-size: 1.5em;
        }
        .container {
            text-align: center;
            padding: 20px;
        }
        .container h2 {
            font-size: 1.8em;
            color: #333;
        }
    </style>
</head>
<body>
<!-- Навигация -->
<div class="navbar">
    <div>
        <!-- Главная кнопка для перехода на список фильмов -->
        <a href="movies">All Movies</a>
        <!-- Проверка, залогинен ли пользователь -->
        <%
            Object user = session.getAttribute("user");
            if (user == null) {
        %>
        <a href="login">Login</a>
        <a href="registration">Registration</a>
        <%
        } else {
        %>
        <a href="user">Account</a>
        <!-- Кнопка logout, как обычная кнопка в navbar -->
        <form action="/MovieReviewPlatform_war_exploded/logout" method="post" style="display: inline;">
            <button type="submit">Logout</button>
        </form>
        <%
            }
        %>
        <a href="${pageContext.request.contextPath}/top-movies">Top 10 Movies</a>
    </div>
</div>

<!-- Контент -->

</body>
</html>
