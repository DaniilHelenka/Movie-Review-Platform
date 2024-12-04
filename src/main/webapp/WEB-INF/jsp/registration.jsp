<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>

<%-- Проверка на наличие ошибки --%>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<div style="color: red;">
    <%= errorMessage %>
</div>
<% } %>

<form action="<%= request.getContextPath() %>/registration" method="post" enctype="multipart/form-data">
    <label for="imageId">Image:
        <input type="file" name="image" id="imageId" required>
    </label>
    <br>
    <label for="name">Name:
        <input type="text" name="name" id="name" required minlength="3">
    </label>
    <br>

    <label for="emailId">Email:
        <input type="email" name="email" id="emailId" required>
    </label>
    <br>

    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId" required minlength="6">
    </label>
    <br>

    <label for="role">Role:
        <select name="role" id="role">
            <%-- Получение списка всех значений enum Role и создание выпадающего списка --%>
            <%
                Role[] roles = Role.values(); // Получаем все значения enum
                for (Role role : roles) {
            %>
            <option value="<%= role.name() %>"><%= role.name() %></option>
            <%
                }
            %>
        </select>
    </label><br>
    <br>

    <button type="submit">Register</button>
</form>
</body>
</html>
