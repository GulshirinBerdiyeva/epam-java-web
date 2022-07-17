<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.addNewMusic" var="addNewMusic" />
<fmt:message bundle="${locale}" key="locale.placeholder.title" var="title" />
<fmt:message bundle="${locale}" key="locale.placeholder.artist" var="artist" />
<fmt:message bundle="${locale}" key="locale.placeholder.price" var="price" />
<fmt:message bundle="${locale}" key="locale.placeholder.image" var="image" />
<fmt:message bundle="${locale}" key="locale.placeholder.audio" var="audio" />
<fmt:message bundle="${locale}" key="locale.button.submit" var="buttonSubmit" />
<fmt:message bundle="${locale}" key="locale.error.message.enterInputParameters" var="enterInputParameters" />
<fmt:message bundle="${locale}" key="locale.title.musicTitle" var="musicTitle" />
<fmt:message bundle="${locale}" key="locale.title.name" var="titleName" />
<fmt:message bundle="${locale}" key="locale.title.money" var="titleMoney" />

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

<main class="add-new-music">
    <form action="${pageContext.request.contextPath}/controller?command=addMusic" method="post" enctype="multipart/form-data">
        <h1>${addNewMusic}</h1>
        <hr/>

        <c:if test="${requestScope.emptyInputParameters}" >
            <br/>
            <h2 id="error-message-editPrice">${enterInputParameters}</h2>
        </c:if>

        <input type="text" name="title" placeholder="${title}" min="3" max="40"
               pattern="(?=.*[A-Za-zА-Яа-яЁё])(?=.*[A-Za-zА-Яа-яЁё])[\wА-Яа-яЁё ']{3,40}"
               title="${musicTitle}" required />

        <input type="text" name="artist" placeholder="${artist}" min="2" max="61"
               pattern="[A-ZА-ЯЁ][a-zа-яё]{1,30}( [A-ZА-ЯЁ][a-zа-яё]{1,30})?"
               title="${titleName}" required />

        <input type="text" name="price" placeholder="${price}" min="1" max="6"
               pattern="(([1-9]|[1-9][0-9])(\.\d{1,2})?)|100(\.0{1,2})?"
               title="${titleMoney}" required>

        <input id="jpg" type="file" name="imageFile" accept=".jpg" required>
        <label for="jpg">${image}</label>

        <input id="mp3" type="file" name="audioFile" accept=".mp3" required>
        <label for="mp3">${audio}</label>

        <input id="submit" type="submit" value="${buttonSubmit}">
    </form>
</main>

</body>

</html>