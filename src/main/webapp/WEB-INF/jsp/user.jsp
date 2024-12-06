<%@ page import="com.example.moviereviewplatform.dto.UserDto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя</title>
</head>
<body>
<h1>Добро пожаловать на страницу пользователя!</h1>

<%
    // Получаем объект пользователя из сессии
    UserDto user = (UserDto) session.getAttribute("user");
    if (user != null) {
%>
<p><strong>ID пользователя:</strong> <%= user.getId() %></p>
<p><strong>Имя пользователя:</strong> <%= user.getName() %></p>
<p><strong>Email:</strong> <%= user.getEmail() %></p>
<%
} else {
%>
<p>Пользователь не найден.</p>
<%
    }
%>
</body>
</html>
