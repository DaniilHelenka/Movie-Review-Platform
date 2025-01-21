<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>

<div class="navbar">
    <div class="logo">
        <h1><fmt:message key="navbar.title"/> </h1>
    </div>
    <div class="nav-links">
        <a href="movies" class="nav-item"><fmt:message key="navbar.allMovies" /></a>
        <% Object user = session.getAttribute("user"); %>
        <% if (user == null) { %>
        <a href="login" class="nav-item"><fmt:message key="navbar.login" /></a>
        <a href="registration" class="nav-item"><fmt:message key="navbar.registration" /></a>
        <% } else { %>
        <a href="user" class="nav-item"><fmt:message key="navbar.account" /></a>
        <form action="/MovieReviewPlatform_war_exploded/logout" method="post" style="display: inline;">
            <a href="#" class="nav-item" onclick="this.closest('form').submit();"><fmt:message key="navbar.logout" /></a>
        </form>
        <% } %>
        <a href="${pageContext.request.contextPath}/top-movies" class="nav-item"><fmt:message key="navbar.topMovies" /></a>

        <!-- Переключатель языка -->
        <div id="locale">
            <form action="<%= request.getContextPath() %>/locate" method="post">
                <button type="submit" name="lang" value="ru_RU">RU</button>
                <button type="submit" name="lang" value="en_US">EN</button>
            </form>
        </div>
        <fmt:setLocale value="${sessionScope.lang != null
                            ? sessionScope.lang
                            : (param.lang != null ? param.lang : 'en_US')}"/>
        <fmt:setBundle basename="translations" />
    </div>
</div>

<!-- Стили для улучшенного header -->
<style>
    /* Основной стиль для навигации */
    .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #2d3436; /* Темный фон для навигации */
        padding: 20px 40px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    /* Логотип */
    .logo h1 {
        color: #ffffff;
        font-size: 1.8em;
        margin: 0;
        font-weight: bold;
        letter-spacing: 2px;
    }

    /* Навигационные ссылки */
    .nav-links {
        display: flex;
        align-items: center;
    }

    .nav-item {
        color: #ffffff;
        text-decoration: none;
        margin: 0 15px;
        font-size: 1.1em;
        padding: 10px 20px;
        background-color: #636e72;
        border-radius: 25px;
        transition: background-color 0.3s, color 0.3s;
    }

    /* Эффекты при наведении на ссылки */
    .nav-item:hover {
        background-color: #74b9ff;
        color: #ffffff;
    }

    /* Стили для кнопки выхода */
    .nav-item.logout {
        background-color: #e74c3c;
    }

    .nav-item.logout:hover {
        background-color: #ff6b6b;
    }

    /* Стили для переключателя языка */
    .language-switcher form {
        display: flex;
        align-items: center;
    }

    .language-switcher .nav-item {
        margin-left: 10px;
        background-color: transparent;
        color: #ffffff;
        border: 1px solid #ffffff;
    }

    .language-switcher .nav-item:hover {
        background-color: #74b9ff;
    }
</style>
