<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><fmt:message key="movieAdd.title" /></title>
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

    .add-form {
      background-color: #ffffff;
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 500px;
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

    input, textarea {
      width: 100%;
      padding: 12px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 16px;
      box-sizing: border-box;
    }

    textarea {
      height: 100px;
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
  </style>
</head>
<body>

<header>
  MovieReviewPlatform
</header>

<div class="container">
  <div class="add-form">
    <h1><fmt:message key="movieAdd.title" /></h1>
    <form action="<%= request.getContextPath() + "/movies/add" %>" method="POST" enctype="multipart/form-data">
      <label for="name"><fmt:message key="movieAdd.nameLabel" /></label>
      <input type="text" id="name" name="name" placeholder="Название фильма" required>

      <label for="genre"><fmt:message key="movieAdd.genreLabel" /></label>
      <input type="text" id="genre" name="genre" placeholder="Жанр" required>

      <label for="description"><fmt:message key="movieAdd.descriptionLabel" /></label>
      <textarea id="description" name="description" placeholder="Описание" required></textarea>

      <label for="poster_url"><fmt:message key="movieAdd.posterLabel" /></label>
      <input type="file" id="poster_url" name="poster_url" accept="image/*" required>

      <label for="release_date"><fmt:message key="movieAdd.releaseDateLabel" /></label>
      <input type="date" id="release_date" name="release_date" required>

      <button type="submit"><fmt:message key="movieAdd.button" /></button>
    </form>

    <%-- Обработка ошибок, если они переданы через request attribute --%>
    <%
      String error = (String) request.getAttribute("error");
      if (error != null) {
    %>
    <div class="error">
      <%= error %>
    </div>
    <% } %>
  </div>
</div>

</body>
</html>
