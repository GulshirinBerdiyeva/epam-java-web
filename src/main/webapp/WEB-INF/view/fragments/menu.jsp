<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.menu.main" var="main" />
<fmt:message bundle="${local}" key="local.menu.albums" var="albums" />
<fmt:message bundle="${local}" key="local.menu.playlist" var="playlist" />
<fmt:message bundle="${local}" key="local.menu.profile" var="profile" />
<fmt:message bundle="${local}" key="local.menu.createAlbum" var="createAlbum" />
<fmt:message bundle="${local}" key="local.menu.addMusic" var="addNewMusic" />
<fmt:message bundle="${local}" key="local.menu.clients" var="clients" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<nav class="menu-wrapper">
    <a href="${pageContext.request.contextPath}/controller?command=main">${main}</a>
    <a href="${pageContext.request.contextPath}/controller?command=albums">${albums}</a>

    <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}" >
        <a href="${pageContext.request.contextPath}/controller?command=playlist">${playlist}</a>
        <a href="${pageContext.request.contextPath}/controller?command=profilePage">${profile}</a>
    </c:if>

    <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}" >
        <a href="${pageContext.request.contextPath}/controller?command=createAlbumPage">${createAlbum}</a>
        <a href="${pageContext.request.contextPath}/controller?command=addMusicPage">${addNewMusic}</a>
        <a href="${pageContext.request.contextPath}/controller?command=clients">${clients}</a>
    </c:if>
</nav>

</body>

</html>