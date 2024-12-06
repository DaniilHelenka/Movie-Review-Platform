<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout Example</title>
</head>
<body>

<%
    // Проверяем, существует ли пользователь в сессии
    Object user = session.getAttribute("user");
    if (user != null) {
%>
<!-- Если пользователь есть, отображаем кнопку выхода -->
<form action="<%= request.getContextPath() %>/logout" method="post">
    <button type="submit">Logout</button>
</form>
<%
    }
%>

</body>
</html>

