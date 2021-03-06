<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.no.playlist" var="noPlaylist"/>

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="playlist-body">

<header>
    <jsp:include page="fragments/header.jsp" />
</header>

<nav>
    <jsp:include page="fragments/menu.jsp" />
</nav>

<main>
    <div class="container">
        <div class="music-container">
            <c:if test="${requestScope.noPlaylist}" >
                <br/>
                <h2 id="error-message-editPrice">${noPlaylist}</h2>
            </c:if>

            <c:if test="${!empty requestScope.playlist}" >
                <c:forEach items="${requestScope.playlist}" var="playlist" >
                    <div class="box">
                        <div class="image">
                            <img src="${pageContext.request.contextPath}/controller?command=getResource&type=image&fileName=${playlist.music.imageFileName}" alt="">
                        </div>

                        <div class="music">
                            <h2><c:out value="${playlist.music.artist}" /></h2>
                            <h2><c:out value="${playlist.music.title}" /></h2>
                            <br/>
                            <audio controls>
                                <source src="${pageContext.request.contextPath}/controller?command=getResource&type=music&fileName=${playlist.music.audioFileName}" type="audio/mpeg">
                            </audio>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</main>

</body>

</html>