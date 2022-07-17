<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.menu.createAlbum" var="createAlbum" />
<fmt:message bundle="${locale}" key="locale.button.createAlbum" var="buttonCreateAlbum" />
<fmt:message bundle="${locale}" key="locale.placeholder.albumTitle" var="albumTitle" />
<fmt:message bundle="${locale}" key="locale.artist" var="artist" />
<fmt:message bundle="${locale}" key="locale.title" var="title" />
<fmt:message bundle="${locale}" key="locale.add" var="add" />
<fmt:message bundle="${locale}" key="locale.error.message.enterInputParameters" var="enterInputParameters" />
<fmt:message bundle="${locale}" key="locale.no.musics" var="noMusics" />
<fmt:message bundle="${locale}" key="locale.title.musicTitle" var="musicTitle" />

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

<main class="album-main">
    <form action="${pageContext.request.contextPath}/controller?command=addAlbum" method="post" >
        <div class="albumTitle-button-wrapper" >
            <h1>${createAlbum}</h1>

            <c:if test="${cookie.get('emptyInputParameters').value != null}" >
                <br/>
                <h2>${enterInputParameters}</h2>
                <br/>
            </c:if>

            <div class="create-album-input-button">
                <input type="text" name="albumTitle" placeholder="${albumTitle}" min="3" max="40"
                       pattern="(?=.*[A-Za-zА-Яа-яЁё])(?=.*[A-Za-zА-Яа-яЁё])[\wА-Яа-яЁё ']{3,40}"
                       title="${musicTitle}" required />
                <button id="search-button" type="submit">${buttonCreateAlbum}</button>
            </div>
        </div>

        <div class="table-wrapper" >
            <c:if test="${requestScope.noMusics}" >
                <br/>
                <h2 id="error-message-editPrice">${noMusics}</h2>
            </c:if>

            <table class="table">
                <thead id="table-thead">
                <tr>
                    <th id="thead-number">№</th>
                    <th>${artist}</th>
                    <th>${title}</th>
                    <th>${add}</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${!empty requestScope.musics}" >
                    <c:forEach items="${requestScope.musics}" var="music" varStatus="i">
                        <tr>
                            <th id="body-number"><c:out value="${i.index+1}" /></th>
                            <th><c:out value="${music.artist}" /></th>
                            <th><c:out value="${music.title}" /></th>
                            <th id="checkbox-input"> <input type="checkbox" name="albumElements" value="${music.id}"> </th>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </form>
</main>

</body>

</html>