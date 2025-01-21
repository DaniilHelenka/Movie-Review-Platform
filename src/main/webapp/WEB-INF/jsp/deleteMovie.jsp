<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="movieDelete.title" /></title>
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

        .delete-form {
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
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #d63031;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #e17055;
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
    <div class="delete-form">
        <h1><fmt:message key="movieDelete.title" /></h1>
        <form action="<%= request.getContextPath() %>/movies/delete" method="post">
            <label for="id"><fmt:message key="movieDelete.labelId" /></label>
            <input type="number" id="id" name="id" required>
            <button type="submit"><fmt:message key="movieDelete.button" /></button>
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
