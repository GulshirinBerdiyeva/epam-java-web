<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.currency.unit" var="currencyUnit" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="music-inform-wrapper">
    <figure>
        <img src="${pageContext.request.contextPath}/controller?command=getResource&type=image&fileName=${sessionScope.selectedMusic.imageFileName}">
    </figure>

    <table class="music-text-inform">
        <tr>
            <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=singer.jpg" align="absmiddle" ></th>
            <th><h2>${sessionScope.selectedMusic.artist}</h2></th>
        </tr>
        <tr>
            <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=music-note.jpg" align="absmiddle" ></th>
            <th><h2>${sessionScope.selectedMusic.title}</h2></th>
        </tr>
        <tr>
            <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=cost.jpg" align="absmiddle" ></th>
            <th><h2>${currencyUnit} ${sessionScope.selectedMusicPrice}</h2></th>
        </tr>
    </table>
</main>

</body>

</html>