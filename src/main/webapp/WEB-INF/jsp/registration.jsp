
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">
            ${errorMessage}
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <!-- CSRF Token -->
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <label for="name">Name:
        <input type="text" name="name" id="name" required minlength="3">
    </label>
    <br>

    <label for="emailId">Email:
        <input type="email" name="email" id="emailId" required>
    </label>
    <br>

    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId" required minlength="6">
    </label>
    <br>
    <label for="role">Role:
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label><br>
    <br>

    <button type="submit">Register</button>
</form>
</body>
</html>
