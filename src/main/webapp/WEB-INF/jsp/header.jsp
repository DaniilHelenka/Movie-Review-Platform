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
        .container {
            text-align: center;
            padding: 20px;
        }
        .container h2 {
            font-size: 1.8em;
            color: #636e72;
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
            <a href="#" onclick="this.closest('form').submit();"
               style="background-color: #b2bec3; padding: 10px 20px; color: #636e72; text-decoration: none; transition: background-color 0.3s ease, color 0.3s ease; border: none; box-shadow: none;">
                Logout
            </a>

        </form>
        <% } %>
        <a href="${pageContext.request.contextPath}/top-movies">Top 10 Movies</a>
    </div>
</div>

<!-- Контент -->

</body>
</html>
