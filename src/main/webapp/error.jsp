<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.title.error" var="titleError" />
<fmt:message bundle="${local}" key="local.error.message.invalidCommand" var="invalidCommand" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/styles.css">
    <title>${titleError}</title>
</head>

<body>

<div class="error-wrapper">
    <c:if test="${requestScope.error != null}">
        <h2>${requestScope.error}</h2>
    </c:if>

    <c:if test="${requestScope.invalidCommand}">
        <h2>${invalidCommand}</h2>
    </c:if>
</div>

</body>

</html>
