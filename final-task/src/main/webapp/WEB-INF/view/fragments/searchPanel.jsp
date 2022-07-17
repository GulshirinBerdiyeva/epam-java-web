<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.placeholder.search.musicByTitle" var="musicByTitle"/>
<fmt:message bundle="${locale}" key="locale.placeholder.search.musicByArtist" var="musicByArtist"/>
<fmt:message bundle="${locale}" key="locale.button.search" var="buttonSearch" />
<fmt:message bundle="${locale}" key="locale.title.musicTitle" var="musicTitle" />
<fmt:message bundle="${locale}" key="locale.title.name" var="titleName" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main>
    <div class="search-wrapper">
        <form action="${pageContext.request.contextPath}/controller?command=searchMusic" method="post" >
            <div id="left-search" class="search-input-button-wrapper" >
                <input type="text" name="artist" placeholder="${musicByArtist}" min="2" max="61"
                       pattern="[A-ZА-ЯЁ][a-zа-яё]{1,30}( [A-ZА-ЯЁ][a-zа-яё]{1,30})?"
                       title="${titleName}" required />

                <button id="search-button" type="submit" >${buttonSearch}</button>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/controller?command=searchMusic" method="post" >
            <div id="right-search" class="search-input-button-wrapper" >
                <input type="text" name="title" placeholder="${musicByTitle}" min="3" max="40"
                       pattern="(?=.*[A-Za-zА-Яа-яЁё])(?=.*[A-Za-zА-Яа-яЁё])[\wА-Яа-яЁё ']{3,40}"
                       title="${musicTitle}" required />

                <button id="search-button2" type="submit">${buttonSearch}</button>
            </div>
        </form>
    </div>
</main>

</body>

</html>