<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.dto.ReviewDto" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.moviereviewplatform.dto.MovieDto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="reviews.title" /></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f5f3;
            color: #333;
        }
        h1, h2 {
            text-align: center;
            color: #2d3436;
            margin-top: 20px;
            font-size: 2em;
        }
        .movie-info {
            text-align: center;
            margin: 20px auto;
        }
        .movie-info img {
            width: 200px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .movie-info h2 {
            margin: 10px 0;
            color: #0984e3;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0 auto;
            max-width: 900px;
        }
        li {
            background-color: #ffffff;
            margin: 20px auto;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease;
        }
        li:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        li strong {
            color: #0984e3;
        }
        p {
            text-align: center;
            color: #636e72;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
        }
        button {
            background-color: #0984e3;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 20px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #74b9ff;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<h1><fmt:message key="reviews.title" /></h1>

<div class="movie-info">
    <h2><fmt:message key="reviews.movie" />: <%= request.getAttribute("movieName") %></h2>
    <img src="<%= request.getContextPath() %>/images/<%=request.getAttribute("poster")%>" alt="<fmt:message key='reviews.poster' />">
</div>

<%
    List<ReviewDto> reviews = (List<ReviewDto>) request.getAttribute("reviews");
    Integer movieId = (Integer) request.getAttribute("movieId");
    if (reviews != null && !reviews.isEmpty()) {
%>
<h2><fmt:message key="reviews.reviews" /></h2>
<ul>
    <%
        for (ReviewDto review : reviews) {
            LocalDateTime createdAt = review.getCreated_at();
            Date date = java.sql.Timestamp.valueOf(createdAt);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String formattedDate = sdf.format(date);
    %>
    <li>
        <strong>Id:</strong> <%= review.getId() %> <br>
        <strong><fmt:message key="reviews.rating" />:</strong> <%= review.getRating() %> <br>
        <strong><fmt:message key="reviews.comment" />:</strong> <%= review.getComments() %> <br>
        <strong><fmt:message key="reviews.date" />:</strong> <%= formattedDate %> <br>
    </li>
    <%
        }
    } else {
    %>
    <p><fmt:message key="reviews.noReviews" /></p>
    <% } %>
</ul>

<h2><fmt:message key="reviews.addReview" />:</h2>
<form action="${pageContext.request.contextPath}/review" method="post">
    <input type="hidden" name="movie_id" value="<%= movieId %>">

    <label for="rating"><fmt:message key="reviews.rating" /></label>
    <input type="number" name="rating" id="rating" min="1" max="10" required>

    <label for="comments"><fmt:message key="reviews.comment" /></label>
    <textarea name="comments" id="comments" rows="4" required></textarea>

    <button type="submit"><fmt:message key="reviews.addButton" /></button>
</form>
</body>
</html>
