<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pgnt" uri="paginationTag" %>

<%@ page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.want.to.buy" var="buttonWantToBuy" />
<fmt:message bundle="${local}" key="local.button.comments" var="buttonComments" />
<fmt:message bundle="${local}" key="local.button.edit" var="buttonEdit" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="slide-body">

<main class="slide-wrapper">

    <c:if test="${requestScope.showMusics}" >
        <c:set var="musics" value="${sessionScope.musics}" />
        <c:set var="description" value="musics" scope="session" />
    </c:if>

    <c:if test="${requestScope.showAlbum}" >
        <c:set var="musics" value="${sessionScope.album}" />
        <c:set var="description" value="album" scope="session" />
    </c:if>

    <c:if test="${requestScope.showSearchingMusics}" >
        <c:set var="musics" value="${sessionScope.searchingMusics}" />
        <c:set var="description" value="searchingMusics" scope="session" />
    </c:if>

    <c:if test="${sessionScope.musics.get(0) != null}" >
        <c:set var="music" value="${sessionScope.musics.get(0)}" />
    </c:if>

    <c:if test="${requestScope.music != null}" >
        <c:set var="music" value="${requestScope.music}" />
    </c:if>

    <c:if test="${sessionScope.size >= 1}" >
        <div class="slide">
            <img src="${pageContext.request.contextPath}/controller?command=getResource&imageFileName=${music.imageFileName}" alt="">
            <div class="description">
                <h2><b>${music.artist}<br>${music.title}</b></h2>
                <br>
                <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}">
                    <audio controls controlsList="nodownload">
                        <source src="${pageContext.request.contextPath}/controller?command=getResource&audioFileName=${music.audioFileName}" type="audio/mpeg">
                    </audio>

                    <br/>
                    <br/>
                    <form action="${pageContext.request.contextPath}/controller?command=selectMusic" method="post" >
                        <button type="submit" name="selectedMusicId" value="${music.id}">${buttonEdit}</button>
                    </form>
                </c:if>

                <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}">
                    <form action="${pageContext.request.contextPath}/controller?command=selectMusic" method="post" >
                        <button type="submit" name="selectedMusicId" value="${music.id}">${buttonWantToBuy}</button>
                    </form>
                </c:if>
            </div>
        </div>

        <br/>
        <div class="pagination" >
            <pgnt:paginationTag totalPageCount="${sessionScope.size}" viewPageCount="3" action="${pageContext.request.contextPath}/controller?command=getMusic&type=${description}" />
        </div>
    </c:if>

</main>

</body>

</html>