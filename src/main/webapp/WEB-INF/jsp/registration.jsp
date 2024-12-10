<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.entity.Role" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <style>
        /* Общие стили для страницы */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            flex-direction: column;
            height: 100vh;
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
        }

        header a {
            color: #fff;
            text-decoration: none;
            margin-right: 15px;
        }

        header a:hover {
            text-decoration: underline;
        }

        /* Контейнер для формы регистрации */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            margin-top: 100px; /* Отступ сверху, чтобы форма не перекрывалась с хедером */
        }

        .registration-form {
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%; /* Уменьшаем ширину формы на 20% */
            max-width: 500px;
            text-align: center;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            font-size: 16px;
            margin-bottom: 10px;
            display: block;
            text-align: left;
            color: #555;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }

        button:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            margin-top: 15px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<!-- Контейнер для формы регистрации -->
<div class="container">
    <div class="registration-form">
        <h1>Registration</h1>

        <!-- Проверка на наличие ошибки -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error">
            <%= errorMessage %>
        </div>
        <% } %>

        <!-- Форма регистрации -->
        <form action="<%= request.getContextPath() %>/registration" method="post" enctype="multipart/form-data">
            <label for="imageId">Image:
                <input type="file" name="image" id="imageId" accept="image/* required>
            </label>
            <br>

            <label for="name" >Name:
                <input type="text" name="name" id="name" required minlength="3">
            </label><br>

            <label for="emailId">Email:
                <input type="email" name="email" id="emailId" required>
            </label><br>

            <label for="passwordId">Password:
                <input type="password" name="password" id="passwordId" required minlength="6">
            </label><br>

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
            </label><br><br>

            <button type="submit">Register</button>
        </form>

    </div>
</div>

</body>
</html>
