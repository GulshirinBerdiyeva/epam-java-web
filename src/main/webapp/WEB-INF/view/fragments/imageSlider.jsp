<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="userLogin" var="login" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/style.css" />
</head>

<body>

<main class="col">
    <c:forEach items="${musics}" var="music">
        <div class="card">
            <img src="${music.imagePath}" alt="">
            <h2>${music.title}</h2>
        </div>
    </c:forEach>

</main>

</body>

</html>