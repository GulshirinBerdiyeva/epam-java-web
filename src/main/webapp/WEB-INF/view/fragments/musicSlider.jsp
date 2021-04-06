<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.want.to.buy" var="buttonWantToBuy" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="col">

    <c:forEach items="${sessionScope.musics}" var="music">
        <div class="card">
            <img src="${music.imagePath}" alt="">
            <div class="description">
                <h2><b>${music.artist}<br>${music.title}</b></h2>
                <br>

                <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}">
                    <audio controls controlsList="nodownload">
                        <source src="${music.audioPath}" type="audio/mpeg">
                    </audio>
                </c:if>

                <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}">
                <form action="${pageContext.request.contextPath}/controller?command=selectMusic" method="post" >
                    <button type="submit" name="selectedMusicTitle" value="${music.title}">${buttonWantToBuy}</button>
                </form>
                </c:if>
            </div>
        </div>
    </c:forEach>

</main>

</body>

</html>