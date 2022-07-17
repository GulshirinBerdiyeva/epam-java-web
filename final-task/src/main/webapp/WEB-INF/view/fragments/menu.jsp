<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.menu.main" var="main" />
<fmt:message bundle="${locale}" key="locale.menu.albums" var="albums" />
<fmt:message bundle="${locale}" key="locale.menu.playlist" var="playlist" />
<fmt:message bundle="${locale}" key="locale.menu.profile" var="profile" />
<fmt:message bundle="${locale}" key="locale.menu.createAlbum" var="createAlbum" />
<fmt:message bundle="${locale}" key="locale.menu.addMusic" var="addNewMusic" />
<fmt:message bundle="${locale}" key="locale.menu.clients" var="clients" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<nav class="menu-wrapper">
    <a href="${pageContext.request.contextPath}/controller?command=mainPage">${main}</a>
    <a href="${pageContext.request.contextPath}/controller?command=albumsPage">${albums}</a>

    <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}" >
        <a href="${pageContext.request.contextPath}/controller?command=playlistPage">${playlist}</a>
        <a href="${pageContext.request.contextPath}/controller?command=profilePage">${profile}</a>
    </c:if>

    <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}" >
        <a href="${pageContext.request.contextPath}/controller?command=createAlbumPage">${createAlbum}</a>
        <a href="${pageContext.request.contextPath}/controller?command=addMusicPage">${addNewMusic}</a>
        <a href="${pageContext.request.contextPath}/controller?command=clientsPage">${clients}</a>
    </c:if>
</nav>

</body>

</html>