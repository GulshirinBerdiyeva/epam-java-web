<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.addNewMusic" var="addNewMusic" />
<fmt:message bundle="${local}" key="local.placeholder.title" var="title" />
<fmt:message bundle="${local}" key="local.placeholder.artist" var="artist" />
<fmt:message bundle="${local}" key="local.placeholder.price" var="price" />
<fmt:message bundle="${local}" key="local.placeholder.image" var="image" />
<fmt:message bundle="${local}" key="local.placeholder.audio" var="audio" />
<fmt:message bundle="${local}" key="local.button.submit" var="buttonSubmit" />
<fmt:message bundle="${local}" key="local.error.message.enterInputParameters" var="enterInputParameters" />

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

<main class="add-nem-music">
    <form action="${pageContext.request.contextPath}/controller?command=addMusic" method="post" enctype="multipart/form-data">
        <h1>${addNewMusic}</h1>
        <hr/>

        <c:if test="${requestScope.emptyInputParameters}" >
            <br/>
            <h2 id="error-message-editPrice">${enterInputParameters}</h2>
        </c:if>

        <input type="text" name="title" placeholder="${title}" />
        <input type="text" name="artist" placeholder="${artist}" />
        <input type="text" name="price" placeholder="${price}" />
        <input type="file" name="imageFile" accept=".jpg" placeholder="${image}" >
        <input type="file" name="audioFile" accept=".mp3" placeholder="${audio}" >
        <input id="submit" type="submit" value="${buttonSubmit}">
    </form>

</main>

</body>

</html>