<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        /* Общие стили для страницы */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            flex-direction: column;
        }

        /* Стили для хедера */
        header {
            background-color: #333;
            color: #fff;
            width: 100%;
            padding: 20px;
            text-align: left;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 10;
        }

        header a {
            color: #fff;
            text-decoration: none;
            margin-right: 15px;
        }

        header a:hover {
            text-decoration: underline;
        }

        /* Стилевые настройки для контейнера формы */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            padding-top: 80px; /* Отступ сверху, чтобы не перекрывать хедером */
        }

        .login-form {
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
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

        input {
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

        a button {
            background-color: #007BFF;
            width: auto;
            margin-top: 10px;
            font-size: 14px;
        }

        a button:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            margin-top: 15px;
            font-size: 14px;
        }

        .forgot-password {
            margin-top: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<!-- Контейнер для формы логина -->
<div class="container">
    <div class="login-form">
        <h1>Login</h1>
        <form action="<%= request.getContextPath() %>/login" method="post">
            <label for="email">Email:
                <input type="text" name="email" id="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" required>
            </label><br>

            <label for="password">Password:
                <input type="password" name="password" id="password" required>
            </label><br>

            <button type="submit">Login</button>

            <a href="<%= request.getContextPath() %>/registration">
                <button type="button">Registration</button>
            </a>

            <% if (request.getParameter("error") != null) { %>
            <div class="error">
                <span>Email or password is not correct</span>
            </div>
            <% } %>
        </form>

        <div class="forgot-password">
            <a href="<%= request.getContextPath() %>/forgot-password">Forgot Password?</a>
        </div>
    </div>
</div>

</body>
</html>
