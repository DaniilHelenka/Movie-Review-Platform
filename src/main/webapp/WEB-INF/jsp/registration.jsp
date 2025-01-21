<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.moviereviewplatform.entity.Role" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="registration.title" /></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f6f5f3;
            margin: 0;
            padding: 0;
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

        .registration-form {
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

        input, select {
            width: 100%;
            padding: 10px;
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
        }

        button:hover {
            background-color: #74b9ff;
        }

        .error {
            color: #d63031;
            font-size: 14px;
            margin-bottom: 15px;
            text-align: left;
        }
    </style>
</head>
<body>

<header>
    <fmt:message key="registration.header" />
</header>

<div class="container">
    <div class="registration-form">
        <h1><fmt:message key="registration.title" /></h1>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error">
            <%= errorMessage %>
        </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/registration" method="post" enctype="multipart/form-data">
            <label for="imageId"><fmt:message key="registration.profileImage" /></label>
            <input type="file" name="image" id="imageId" accept="image/*" required>

            <label for="name"><fmt:message key="registration.name" /></label>
            <input type="text" name="name" id="name" required minlength="3">

            <label for="emailId"><fmt:message key="registration.email" /></label>
            <input type="email" name="email" id="emailId" required>

            <label for="passwordId"><fmt:message key="registration.password" /></label>
            <input type="password" name="password" id="passwordId" required minlength="6">

            <label for="role"><fmt:message key="registration.role" /></label>
            <select name="role" id="role">
                <%
                    Role[] roles = Role.values();
                    for (Role role : roles) {
                %>
                <option value="<%= role.name() %>"><%= role.name() %></option>
                <% } %>
            </select>

            <button type="submit"><fmt:message key="registration.submit" /></button>
        </form>
    </div>
</div>

</body>
</html>
