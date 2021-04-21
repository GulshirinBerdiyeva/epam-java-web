<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.placeholder.search.musicByTitle" var="musicByTitle"/>
<fmt:message bundle="${local}" key="local.placeholder.search.musicByArtist" var="musicByArtist"/>
<fmt:message bundle="${local}" key="local.button.search" var="buttonSearch" />
<fmt:message bundle="${local}" key="local.error.message.musicIsAbsent" var="musicIsAbsent" />
<fmt:message bundle="${local}" key="local.error.message.enterInputParameters" var="enterInputParameters" />
<fmt:message bundle="${local}" key="local.error.message.musicRemoved" var="musicRemoved" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="search-body">

<header>
    <jsp:include page="fragments/header.jsp" />
</header>

<nav>
    <jsp:include page="fragments/menu.jsp" />
</nav>

<main class="search-main">

    <div class="search-wrapper">
        <form action="${pageContext.request.contextPath}/controller?command=searchMusic" method="post" >
            <div id="left-search" class="search-input-button-wrapper" >
                <input type="text" name="artist" placeholder="${musicByArtist}" />
                <button type="submit">${buttonSearch}</button>
            </div>
            <div id="right-search" class="search-input-button-wrapper" >
                <input type="text" name="title" placeholder="${musicByTitle}" />
                <button type="submit">${buttonSearch}</button>
            </div>
        </form>
    </div>

    <c:if test="${requestScope.musicIsAbsent}" >
        <br/>
        <h2 id="error-message-editPrice">${musicIsAbsent}</h2>
    </c:if>

    <c:if test="${requestScope.emptyInputParameters}" >
        <br/>
        <h2 id="error-message-editPrice">${enterInputParameters}</h2>
    </c:if>

    <c:if test="${requestScope.musicRemoved}" >
        <br/>
        <h2 id="error-message-editPrice">${musicRemoved}</h2>
    </c:if>

</main>

</body>

</html>
