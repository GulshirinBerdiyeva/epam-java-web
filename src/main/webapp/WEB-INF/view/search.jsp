<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.placeholder.search.song" var="searchSong"/>
<fmt:message bundle="${local}" key="local.button.search" var="buttonSearch" />
<fmt:message bundle="${local}" key="local.error.message.songIsAbsent" var="songIsAbsentMessage" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="search-body">

<nav>
    <jsp:include page="fragments/header.jsp" />
</nav>
<nav>
    <jsp:include page="fragments/menu.jsp" />
</nav>

<main class="search-main">

    <div class="search-wrapper">
        <form action="${pageContext.request.contextPath}/controller?command=selectMusic" method="post" >
            <input type="text" name="selectedMusicTitle" placeholder="${searchSong}" />
            <button type="submit">${buttonSearch}</button>
        </form>
    </div>

    <c:if test="${requestScope.songIsAbsent != null}" >
        <br/>
        <h2>${songIsAbsentMessage}</h2>
    </c:if>

</main>

</body>

</html>
