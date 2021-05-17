<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.title.error" var="titleError" />
<fmt:message bundle="${local}" key="local.error.invalid.command" var="invalidCommand" />
<fmt:message bundle="${local}" key="local.error.unknown.command.type" var="unknownCommandType" />
<fmt:message bundle="${local}" key="local.error.parameter.null" var="parameterNull" />
<fmt:message bundle="${local}" key="local.error" var="errorMessage" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/styles.css">
    <title>${titleError}</title>
</head>

<body>

<div class="error-wrapper">
    <c:choose>
        <c:when test="${fn:containsIgnoreCase(requestScope.error, 'Invalid command for this user!')}">
            <h2>${invalidCommand}</h2>
        </c:when>

        <c:when test="${fn:containsIgnoreCase(requestScope.error, 'Unknown type of command!')}">
            <h2>${unknownCommandType}${requestScope.error.substring(requestScope.error.lastIndexOf('!') + 1)}</h2>
        </c:when>

        <c:when test="${fn:containsIgnoreCase(requestScope.error, 'Parameter is NULL!')}">
            <h2>${parameterNull}</h2>
        </c:when>

        <c:otherwise>
            <h2>${errorMessage}</h2>
        </c:otherwise>
    </c:choose>
</div>

</body>

</html>