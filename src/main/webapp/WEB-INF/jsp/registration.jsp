
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
<label for="name">Name:
    <input type="text" name="name" id="name">
</label><br>
<label for="emailId">Email:
    <input type="text" name="email" id="emailId">
</label><br>
<label for="passwordId">Password:
    <input type="password" name="password" id="passwordId">
</label><br>
<button type="submit">Send</button>
<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
    <c:forEach var="error" items="${requestScope.errors}">
        <span>${error.message}</span>
        </form>
        </body>
        </html>
