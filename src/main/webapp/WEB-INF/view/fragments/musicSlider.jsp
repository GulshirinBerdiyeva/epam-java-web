<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="buy" var="buy" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="col">
    <c:forEach items="${musics}" var="music">
        <div class="card">
            <img src="${music.imagePath}" alt="">
            <div class="description">
                <h2><b>${music.artist}<br>${music.title}</b></h2>
                <br>

                <c:if test="${admin != null}">
                    <audio controls controlsList="nodownload">
                        <source src="${music.audioPath}" type="audio/mpeg">
                    </audio>
                </c:if>

                <c:if test="${admin == null}">
                <form action="${pageContext.request.contextPath}/controller?command=purchase" method="post" >
                    <button type="submit" name="selectedMusic" value="${music.title}">Want to buy</button>
                </form>
                </c:if>

            </div>
        </div>
    </c:forEach>
</main>

</body>

</html>