<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.dto.ReviewDto" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.moviereviewplatform.dto.MovieDto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Отзывы</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1, h2 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }
        .movie-info {
            text-align: center;
            margin: 20px auto;
        }
        .movie-info img {
            width: 200px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .movie-info h2 {
            margin: 10px 0;
            color: #4caf50;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #fff;
            margin: 20px auto;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 800px;
        }
        li strong {
            color: #4caf50;
        }
        p {
            text-align: center;
            color: #555;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 15px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            background-color: #4caf50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }

        /* Новый стиль для header */
        header {
            background-color: #343a40;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header .logo {
            font-size: 24px;
            font-weight: bold;
        }

        header nav {
            display: flex;
        }

        header nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
            font-size: 18px;
        }

        header nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<h1>Отзывы о фильме</h1>


<div class="movie-info">
    <h2>Movie: <%= request.getAttribute("movieName") %></h2>
    <img src="<%= request.getAttribute("poster") %>" alt="Movie Poster" style="max-width: 300px; max-height: 450px;">
</div>

<%
    List<ReviewDto> reviews = (List<ReviewDto>) request.getAttribute("reviews");
    Integer movieId = (Integer) request.getAttribute("movieId");
    if (reviews != null && !reviews.isEmpty()) {
%>
<h2>Отзывы:</h2>
<ul>
    <%
        for (ReviewDto review : reviews) {
            // Форматирование даты и времени
            LocalDateTime createdAt = review.getCreated_at();
            Date date = java.sql.Timestamp.valueOf(createdAt);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String formattedDate = sdf.format(date);
    %>
    <li>
        <strong>Id:</strong> <%= review.getId() %> <br>
        <strong>Рейтинг:</strong> <%= review.getRating() %> <br>
        <strong>Комментарий:</strong> <%= review.getComments() %> <br>
        <strong>Дата:</strong> <%= formattedDate %> <br>
    </li>
    <%
        }
    } else {
    %>
    <p>Отзывов нет.</p>
    <% } %>
</ul>

<h2>Добавить отзыв:</h2>
<form action="${pageContext.request.contextPath}/review" method="post">
    <input type="hidden" name="movie_id" value="<%= movieId %>">

    <label for="rating">Рейтинг (1-10):</label>
    <input type="number" name="rating" id="rating" min="1" max="10" required>

    <label for="comments">Комментарий:</label>
    <textarea name="comments" id="comments" rows="4" required></textarea>

    <button type="submit">Добавить отзыв</button>
</form>
</body>
</html>
