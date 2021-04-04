<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.placeholder.search.song" var="searchSong"/>
<fmt:message bundle="${local}" key="local.button.search" var="buttonSearch" />
<fmt:message bundle="${local}" key="local.error.message.songIsAbsent" var="songIsAbsentMessage" />
<fmt:message bundle="${local}" key="local.currency.unit" var="currencyUnit" />
<fmt:message bundle="${local}" key="local.button.buy" var="buttonBuy" />
<fmt:message bundle="${local}" key="local.button.edit" var="buttonEdit" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="search-body">

<nav>
    <jsp:include page="header.jsp" />
</nav>
<nav>
    <jsp:include page="menu.jsp" />
</nav>

<main class="search-main">

    <div class="search-wrapper">
        <form action="${pageContext.request.contextPath}/controller?command=selectMusic" method="post" >
            <input type="text" name="selectedMusicTitle" placeholder="${searchSong}">
            <button type="submit">${buttonSearch}</button>
        </form>
    </div>

    <c:if test="${requestScope.songIsAbsent != null}" >
        <br/>
        <h2>${songIsAbsentMessage}</h2>
    </c:if>

    <c:if test="${requestScope.selectedMusic != null}" >
        <div class="purchase-wrapper">
            <div class="purchase-image">
                <img src="${requestScope.selectedMusic.imagePath}">
            </div>

            <div class="purchase-text">
                <h2>${requestScope.selectedMusic.artist}</h2>
                <h2>${requestScope.selectedMusic.title}</h2>
                <h2>${currencyUnit} ${requestScope.selectedMusic.price}</h2>
            </div>

            <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}" >
                <div class="buy-button">
                    <form action="${pageContext.request.contextPath}/controller?command=buy" method="post" >
                        <button type="submit">${buttonBuy}</button>
                    </form>
                </div>

                <c:if test="">
                    <div class="order-confirmation">
                        <form action="${pageContext.request.contextPath}/controller?command=confirm" method="post" >

                        </form>
                    </div>
                </c:if>

                <c:if test="">
                    <div class="order-rejection">
                        <form action="${pageContext.request.contextPath}/controller?command=cancel" method="post" >

                        </form>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}" >
                <audio controls controlsList="nodownload">
                    <source src="${requestScope.selectedMusic.audioPath}" type="audio/mpeg">
                </audio>

                <div class="buy-button">
                    <form action="${pageContext.request.contextPath}/controller?command=edit" method="post" >
                        <button type="submit">${buttonEdit}</button>
                    </form>
                </div>

            </c:if>
        </div>
    </c:if>

</main>

</body>

</html>
