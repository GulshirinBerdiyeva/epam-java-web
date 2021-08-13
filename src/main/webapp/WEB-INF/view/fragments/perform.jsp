<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.currency.unit" var="currencyUnit" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="music-inform-wrapper">
    <figure>
        <img src="${pageContext.request.contextPath}/controller?command=getResource&type=image&fileName=${requestScope.music.imageFileName}">
    </figure>

    <table class="music-text-inform">
        <tr>
            <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=singer.jpg" align="absmiddle" ></th>
            <th><h2><c:out value="${requestScope.music.artist}" /></h2></th>
        </tr>
        <tr>
            <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=musicNote.jpg" align="absmiddle" ></th>
            <th><h2><c:out value="${requestScope.music.title}" /></h2></th>
        </tr>
        <tr>
            <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=cost.jpg" align="absmiddle" ></th>
            <th><h2><c:out value="${currencyUnit} ${requestScope.musicPrice}" /></h2></th>
        </tr>
    </table>
</main>

</body>

</html>