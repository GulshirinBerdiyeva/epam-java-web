<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="music" var="music" />
<fmt:message bundle="${loc}" key="logoutButton" var="logoutButton" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<div class="header-wrapper">
    <h1>${music}</h1>

    <div class="russian">
        <form action="${pageContext.request.contextPath}/controller?command=russian" method="post" >
            <button type="submit" />
        </form>
    </div>

    <div class="english">
        <form action="${pageContext.request.contextPath}/controller?command=english" method="post" >
            <button type="submit" />
        </form>
    </div>

    <div class="logout">
        <form action="${pageContext.request.contextPath}/controller?command=logout" method="post" >
            <button type="submit">${logoutButton}</button>
        </form>
    </div>
</div>

</body>

</html>
