<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.menu.createAlbum" var="createAlbum" />
<fmt:message bundle="${local}" key="local.button.createAlbum" var="buttonCreateAlbum" />
<fmt:message bundle="${local}" key="local.placeholder.albumTitle" var="albumTitle" />
<fmt:message bundle="${local}" key="local.artist" var="artist" />
<fmt:message bundle="${local}" key="local.title" var="title" />
<fmt:message bundle="${local}" key="local.add" var="add" />
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

<main>
    <form action="${pageContext.request.contextPath}/controller?command=addAlbum" method="post" >
        <div class="albumTitle-button-wrapper" >
            <h1 id="album-title">${createAlbum}</h1>

            <c:if test="${requestScope.emptyInputParameters}" >
                <br/>
                <h2 id="error-message-addAlbum">${enterInputParameters}</h2>
                <br/>
            </c:if>

            <div class="create-album-input-button">
                <input type="text" name="albumTitle" placeholder="${albumTitle}">
                <button type="submit">${buttonCreateAlbum}</button>
            </div>
        </div>

        <div class="table-wrapper" >
            <table class="table">
                <thead>
                <tr>
                    <th id="thead-number">№</th>
                    <th>${artist}</th>
                    <th>${title}</th>
                    <th>${add}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.musics}" var="music" varStatus="i">
                    <tr>
                        <th id="body-number">${i.index+1}</th>
                        <th>${music.artist}</th>
                        <th>${music.title}</th>
                        <th id="checkbox-input"> <input type="checkbox" name="albumElements" value="${music.id}"> </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</main>
</body>
</html>