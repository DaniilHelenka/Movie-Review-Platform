<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/login" method="post">
    <label for="email">Email:
        <input type="text" name="email" id="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" required>
    </label><br>
    <label for="password">Password:
        <input type="password" name="password" id="password" required>
    </label><br>
    <button type="submit">Login</button>
    <a href="<%= request.getContextPath() %>/registration">
        <button type="button">Registration</button>
    </a>
    <% if (request.getParameter("error") != null) { %>
    <div style="color: red">
        <span>Email or password is not correct</span>
    </div>
    <% } %>
</form>
</body>
</html>
