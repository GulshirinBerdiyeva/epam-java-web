<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.error.message.musicIsAbsent" var="musicIsAbsent" />
<fmt:message bundle="${locale}" key="locale.error.message.enterInputParameters" var="enterInputParameters" />
<fmt:message bundle="${locale}" key="locale.error.message.musicRemoved" var="musicRemoved" />
<fmt:message bundle="${locale}" key="locale.no.musics" var="noMusics" />
<fmt:message bundle="${locale}" key="locale.error.invalidParameter" var="invalidParameter" />
<fmt:message bundle="${locale}" key="locale.error.couldNotLoadResources" var="couldNotLoadResources" />

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

<div class="main-search">
    <jsp:include page="fragments/searchPanel.jsp" />

    <c:if test="${requestScope.noMusics}" >
        <br/>
        <h2 id="error-message-editPrice">${noMusics}</h2>
    </c:if>

     <c:if test="${requestScope.musicIsAbsent}" >
        <br/>
        <h2 id="error-message-editPrice">${musicIsAbsent}</h2>
    </c:if>

    <c:if test="${requestScope.musicRemoved}" >
        <br/>
        <h2 id="error-message-editPrice">${musicRemoved}</h2>
    </c:if>

    <c:if test="${requestScope.couldNotLoadResources}" >
        <br/>
        <h2 id="error-message-editPrice">${couldNotLoadResources}</h2>
    </c:if>

    <c:if test="${cookie.get('emptyInputParameters').value != null}" >
        <br/>
        <h2 id="error-message-editPrice">${enterInputParameters}</h2>
    </c:if>

    <c:if test="${(requestScope.invalidParameter) || (cookie.get('invalidParameter').value != null)}" >
        <br/>
        <h2 id="error-message-editPrice">${invalidParameter}</h2>
    </c:if>


</div>

<main>
    <c:if test="${!requestScope.noMusics && !requestScope.musicIsAbsent && !requestScope.musicRemoved && !requestScope.couldNotLoadResources}" >
        <jsp:include page="fragments/musicSlider.jsp" />
    </c:if>
</main>

</body>

</html>