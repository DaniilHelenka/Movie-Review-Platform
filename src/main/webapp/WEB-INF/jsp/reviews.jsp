<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.dto.ReviewDto" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviews</title>
</head>
<body>
<h1>Отзывы о фильме</h1>

<!-- Проверка наличия отзывов -->
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
        <strong>Комментарий:</strong> <%= review.getComment() %> <br>
        <strong>Дата:</strong> <%= formattedDate %> <br>
        <hr>
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
    <input type="hidden" name="movie_id" value="<%= request.getAttribute("movieId") %>">

    <label for="rating">Рейтинг (1-10):</label>
    <input type="number" name="rating" id="rating" min="1" max="10" required><br>

    <label for="comment">Комментарий:</label><br>
    <textarea name="comment" id="comment" required></textarea><br>

    <button type="submit">Добавить отзыв</button>
</form>
</body>
</html>
