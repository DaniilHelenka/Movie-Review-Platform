<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f5f3;
            color: #333;
        }

        header {
            background-color: #0984e3;
            color: white;
            padding: 15px;
            text-align: center;
            font-size: 24px;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .login-form {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        h1 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #2d3436;
        }

        label {
            display: block;
            font-size: 1em;
            text-align: left;
            margin-bottom: 10px;
            color: #636e72;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #0984e3;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }

        button:hover {
            background-color: #74b9ff;
        }

        .error {
            color: #d63031;
            font-size: 14px;
            margin-top: 15px;
            text-align: left;
        }

        .forgot-password {
            margin-top: 10px;
            font-size: 14px;
            text-align: right;
        }

        .forgot-password a {
            text-decoration: none;
            color: #0984e3;
        }

        .forgot-password a:hover {
            color: #74b9ff;
        }

        .register-button {
            background-color: #6c5ce7;
            color: white;
            text-decoration: none;
            display: inline-block;
            padding: 10px 20px;
            margin-top: 15px;
            font-size: 14px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .register-button:hover {
            background-color: #a29bfe;
        }
    </style>
</head>
<body>

<header>
    MovieReviewPlatform
</header>

<div class="container">
    <div class="login-form">
        <h1>Вход</h1>
        <form action="<%= request.getContextPath() %>/login" method="post">
            <label for="email">Email:</label>
            <input
                    type="text"
                    name="email"
                    id="email"
                    value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"
                    required>

            <label for="password">Пароль:</label>
            <input
                    type="password"
                    name="password"
                    id="password"
                    required>

            <button type="submit">Войти</button>

            <% if (request.getParameter("error") != null) { %>
            <div class="error">
                Неверный email или пароль.
            </div>
            <% } %>
        </form>

        <div class="forgot-password">
            <a href="<%= request.getContextPath() %>/forgot-password">Забыли пароль?</a>
        </div>

        <a href="<%= request.getContextPath() %>/registration" class="register-button">Зарегистрироваться</a>
    </div>
</div>

</body>
</html>
