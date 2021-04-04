<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.create.album" var="buttonCreateAlbum" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="albums-body">

<nav>
    <jsp:include page="header.jsp" />
</nav>
<nav>
    <jsp:include page="menu.jsp" />
</nav>

<main class="albums-main">

    <form class="create-album" action="${pageContext.request.contextPath}/controller?command=createAlbum" method="post">
        <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}">
            <button type="submit">${buttonCreateAlbum}</button>

            <c:if test="${selectMusic != null}">
                <div class="choice-wrapper">
                    <table id="table">
                        <thead>
                        <tr>
                            <th id="thead-number">№</th>
                            <th>Artist</th>
                            <th>Title</th>
                            <th>Add</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${musics}" var="music" varStatus="i">
                        <tr>
                            <th id="body-number">${i.index+1}</th>
                            <th>${music.artist}</th>
                            <th>${music.title}</th>
                            <th> <input type="checkbox" name="selected"> </th>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

        </c:if>
    </form>


</main>

</body>

</html>
