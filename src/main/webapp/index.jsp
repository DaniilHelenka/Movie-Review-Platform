<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieReviewPlatform</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f5f3; /* Светлый фон, как на другой странице */
            color: #333;
        }
        .navbar {
            background-color: #dfe6e9; /* Мягкий серый фон для навигации */
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .navbar a, .navbar button {
            color: #636e72;
            text-decoration: none;
            margin: 0 10px;
            background-color: #b2bec3;
            border: none;
            padding: 10px 20px;
            border-radius: 20px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .navbar a:hover, .navbar button:hover {
            background-color: #74b9ff;
            color: #ffffff;
        }
        .navbar h1 {
            margin: 0;
            font-size: 1.8em;
            color: #2d3436;
        }
        .navbar form {
            margin: 0;
            display: inline;
        }
        main {
            text-align: center;
            padding: 50px;
            color: #636e72; /* Цвет текста для главного контента */
        }
        main h2 {
            font-size: 2.5em;
            color: #2d3436; /* Заголовок с более тёмным оттенком */
            margin-bottom: 20px;
        }
        main p {
            font-size: 1.2em;
            color: #636e72; /* Мягкий текст */
            margin: 0 0 30px 0;
        }
        footer {
            background-color: #dfe6e9; /* Мягкий серый фон для подвала */
            color: #636e72;
            text-align: center;
            padding: 15px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        footer p {
            margin: 0;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<!-- Навигация -->
<div class="navbar">
    <div>
        <h1>MovieReviewPlatform</h1>
    </div>
    <div>
        <a href="movies">All Movies</a>
        <% Object user = session.getAttribute("user"); %>
        <% if (user == null) { %>
        <a href="login">Login</a>
        <a href="registration">Registration</a>
        <% } else { %>
        <a href="user">Account</a>
        <form action="/MovieReviewPlatform_war_exploded/logout" method="post" style="display: inline;">
            <button type="submit">Logout</button>
        </form>
        <% } %>
        <a href="${pageContext.request.contextPath}/top-movies">Top 10 Movies</a>
    </div>
</div>

<!-- Контент -->
<main>
    <h2>Добро пожаловать в MovieReviewPlatform!</h2>
    <p>Здесь вы можете найти лучшие фильмы, оставлять отзывы и делиться впечатлениями.</p>
</main>

<!-- Подвал -->
<footer>
    <p>&copy; 2024 MovieReviewPlatform. Все права защищены.</p>
</footer>

</body>
</html>
