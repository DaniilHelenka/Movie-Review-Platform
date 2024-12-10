<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Добавить фильм</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Добавить фильм</h1>
<form action="<%= request.getContextPath() + "/movies/add" %>" method="POST" enctype="multipart/form-data">
  <label for="name">Название фильма:</label><br>
  <input type="text" id="name" name="name" placeholder="Название фильма" required><br><br>

  <label for="genre">Жанр:</label><br>
  <input type="text" id="genre" name="genre" placeholder="Жанр" required><br><br>

  <label for="description">Описание:</label><br>
  <textarea id="description" name="description" placeholder="Описание" required></textarea><br><br>

  <label for="poster_url">Image:
    <input type="file" name="poster_url" id="poster_url" required>
  </label><br>

  <label for="release_date">Дата выхода:</label><br>
  <input type="date" id="release_date" name="release_date" required><br><br>

  <button type="submit">Добавить фильм</button>
</form>
</body>
</html>
