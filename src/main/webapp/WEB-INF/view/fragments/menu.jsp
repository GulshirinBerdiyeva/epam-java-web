<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.menu.main" var="main" />
<fmt:message bundle="${local}" key="local.menu.albums" var="albums" />
<fmt:message bundle="${local}" key="local.menu.playlist" var="playlist" />
<fmt:message bundle="${local}" key="local.menu.search" var="search" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<nav class="menu-wrapper">
    <a href="${pageContext.request.contextPath}/controller?command=main">${main}</a>
    <a href="${pageContext.request.contextPath}/controller?command=albums">${albums}</a>
    <a href="${pageContext.request.contextPath}/controller?command=playlist">${playlist}</a>
    <a href="${pageContext.request.contextPath}/controller?command=search">${search}</a>
</nav>

</body>

</html>