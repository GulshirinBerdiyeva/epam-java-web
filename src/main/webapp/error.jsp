<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="errorCommand" var="commandError" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/style.css" />
    <title>Error</title>
</head>

<body>

<div class="unknown-command">
    <c:if test="${errorCommand != null}">
        <h2>${commandError}</h2>
    </c:if>
</div>

</body>

</html>
