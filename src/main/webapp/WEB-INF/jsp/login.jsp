<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="page.login.title" /></title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f5f3;
            color: #333;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-form {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }
        .login-form input, .login-form button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-form button {
            background-color: #0984e3;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .login-form button:hover {
            background-color: #74b9ff;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
        <h1><fmt:message key="page.login.title" /></h1>
        <c:if test="${not empty errorMessage}">
            <div class="error">
                <fmt:message key="page.login.error" />
            </div>
        </c:if>
        <label for="email"><fmt:message key="page.login.email" /></label>
        <input type="email" id="email" name="email" required />

        <label for="password"><fmt:message key="page.login.password" /></label>
        <input type="password" id="password" name="password" required />

        <button type="submit"><fmt:message key="page.login.submit.button" /></button>
        <a href="registration"><fmt:message key="page.login.register.button" /></a>
    </form>
</div>
</body>
</html>
