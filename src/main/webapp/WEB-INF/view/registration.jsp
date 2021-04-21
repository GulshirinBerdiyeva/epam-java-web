<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.title.order.music" var="orderMusic" />
<fmt:message bundle="${local}" key="local.placeholder.username" var="username" />
<fmt:message bundle="${local}" key="local.placeholder.password" var="password" />
<fmt:message bundle="${local}" key="local.signUp" var="signUp" />
<fmt:message bundle="${local}" key="local.button.signIn" var="buttonSignIn" />
<fmt:message bundle="${local}" key="local.error.message.errorSignIn" var="errorSignIn" />
<fmt:message bundle="${local}" key="local.error.message.enterInputParameters" var="enterInputParameters" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
    <title>${orderMusic}</title>
</head>

<body class="main-body">

<header>
    <jsp:include page="fragments/header.jsp" />
</header>

<main class="login-form">
    <form action="${pageContext.request.contextPath}/controller?command=signIn" method="post" >
        <h1>${signUp}</h1>

        <c:if test="${requestScope.emptyInputParameters}" >
            <br/>
            <h2 id="error-message-editPrice">${enterInputParameters}</h2>
        </c:if>

        <c:if test="${requestScope.errorSignIn}">
            <br/>
            <h2>${errorSignIn}</h2>
        </c:if>

        <br/>
        <input type="text" name="username" placeholder="${username}" />
        <br/>
        <br/>
        <input type="password" name="password" placeholder="${password}" />
        <button type="submit">${buttonSignIn}</button>
    </form>
</main>

</body>

</html>