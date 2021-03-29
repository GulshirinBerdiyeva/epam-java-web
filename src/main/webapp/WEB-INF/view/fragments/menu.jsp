<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="main" var="main" />
<fmt:message bundle="${loc}" key="albums" var="albums" />
<fmt:message bundle="${loc}" key="playlists" var="playlists" />
<fmt:message bundle="${loc}" key="search" var="search" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/style.css" />
</head>

<body>

<nav class="menu-wrapper">
    <a href="${pageContext.request.contextPath}/controller?command=main">${main}</a>
    <a href="${pageContext.request.contextPath}/controller?command=albums">${albums}</a>
    <a href="${pageContext.request.contextPath}/controller?command=playlists">${playlists}</a>
    <a href="${pageContext.request.contextPath}/controller?command=search">${search}</a>
</nav>

</body>

</html>