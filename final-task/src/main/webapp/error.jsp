<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.title.error" var="titleError" />
<fmt:message bundle="${locale}" key="locale.error.unknown.command.type" var="unknownCommandType" />
<fmt:message bundle="${locale}" key="locale.error.unknown.musics.type" var="unknownMusicsType" />
<fmt:message bundle="${locale}" key="locale.error" var="errorMessage" />
<fmt:message bundle="${locale}" key="locale.login.page" var="loginPage" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/styles.css">
    <title>${titleError}</title>
</head>

<body class="main-body">

<header>
    <jsp:include page="WEB-INF/view/fragments/header.jsp" />
</header>

<main class="login-form" id="error-form">
    <c:if test="${requestScope.error != null}" >
        <c:set var="error" value="${requestScope.error}" scope="page"/>

        <div class="error-wrapper">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(error, 'Unknown type of command!')}">
                    <h2><c:out value="${unknownCommandType}" /> </h2>
                    <h2><c:out value="${error.substring(error.indexOf('!') + 1)}" /></h2>
                </c:when>

                <c:when test="${fn:containsIgnoreCase(error, 'Unknown type of musics description!')}">
                    <h2><c:out value="${unknownMusicsType}" /> </h2>
                    <h2><c:out value="${error.substring(error.indexOf('!') + 1)}" /></h2>
                </c:when>

                <c:otherwise>
                    <h2>${errorMessage}</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <div id="error-link">
        <a href="${pageContext.request.contextPath}/controller?command=logout" >${loginPage}</a>
    </div>
</main>

</body>

</html>