<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

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
            <c:forEach items="${sessionScope.playlists}" var="playlist" >
                <div class="box">
                    <div class="image">
                        <img src="${pageContext.request.contextPath}/controller?command=getResource&imageFileName=${playlist.music.imageFileName}" alt="">
                    </div>
                    <div class="music">
                        <audio src="${pageContext.request.contextPath}/controller?command=getResource&audioFileName=${playlist.music.audioFileName}" controls />
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

</body>

</html>


