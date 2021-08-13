<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.placeholder.albumTitle" var="albumTitle" />
<fmt:message bundle="${locale}" key="locale.no.albums" var="noAlbums" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="main-body">

<header>
    <jsp:include page="fragments/header.jsp" />
</header>

<nav>
    <jsp:include page="fragments/menu.jsp" />
</nav>

<main class="album-main">
    <c:if test="${requestScope.noAlbums}" >
        <br/>
        <h2 id="error-message-editPrice">${noAlbums}</h2>
    </c:if>

    <c:if test="${!empty requestScope.albumsTitle}" >
        <c:forEach items="${requestScope.albumsTitle}" var="title"  >
            <div class="albums" >
                <form action="${pageContext.request.contextPath}/controller?command=albumMusics" method="post" >
                    <button type="submit" name="selectedAlbumTitle" value="${title}"><c:out value="${title}" /></button>
                </form>
            </div>
        </c:forEach>
    </c:if>
</main>

</body>

</html>
