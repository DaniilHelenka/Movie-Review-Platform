<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="navbar.title" /></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f5f3;
            color: #333;
        }
        .navbar {
            background-color: #dfe6e9;
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
            color: #636e72;
        }
        main h2 {
            font-size: 2.5em;
            color: #2d3436;
            margin-bottom: 20px;
        }
        main p {
            font-size: 1.2em;
            color: #636e72;
            margin: 0 0 30px 0;
        }
        footer {
            background-color: #dfe6e9;
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
        <h1><fmt:message key="navbar.title" /></h1>
    </div>
    <div>
        <a href="movies" class="nav-item"><fmt:message key="navbar.allMovies" /></a>
        <% Object user = session.getAttribute("user"); %>
        <% if (user == null) { %>
        <a href="login" class="nav-item"><fmt:message key="navbar.login" /></a>
        <a href="registration" class="nav-item"><fmt:message key="navbar.registration" /></a>
        <% } else { %>
        <a href="user" class="nav-item"><fmt:message key="navbar.account" /></a>
        <form action="/MovieReviewPlatform_war_exploded/logout" method="post" style="display: inline;">
            <button type="submit" class="nav-item"><fmt:message key="navbar.logout" /></button>
        </form>
        <% } %>
        <a href="${pageContext.request.contextPath}/top-movies" class="nav-item"><fmt:message key="navbar.topMovies" /></a>
    </div>
</div>

<!-- Контент -->
<main>
    <h2><fmt:message key="main.welcomeTitle" /></h2>
    <p><fmt:message key="main.description" /></p>
</main>

<!-- Подвал -->
<footer>
    <p><fmt:message key="footer.copyRight" /></p>
</footer>

</body>
</html>