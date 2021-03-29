<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />
<fmt:message bundle="${loc}" key="login" var="login" />
<fmt:message bundle="${loc}" key="errorLogin" var="loginError" />
<fmt:message bundle="${loc}" key="userLogin" var="userLogin" />
<fmt:message bundle="${loc}" key="password" var="password" />
<fmt:message bundle="${loc}" key="loginButton" var="loginButton" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/style.css" />
</head>

<body class="login-body-background-gray">

<header class="login-header">
    <div class="english">
        <form action="${pageContext.request.contextPath}/controller?command=english" method="post" >
            <button type="submit" />
        </form>
    </div>

    <div class="russian">
        <form action="${pageContext.request.contextPath}/controller?command=russian" method="post" >
            <button type="submit" />
        </form>
    </div>
</header>

<main class="login-form">
    <form action="${pageContext.request.contextPath}/controller?command=login" method="post" >
        <h1>${login}</h1>
        <c:if test="${errorLogin != null}">
            <br/>
            <h2>${loginError}</h2>
        </c:if>
        <br/>
        <input type="text" name="login" placeholder="${userLogin}"/>
        <br/>
        <br/>
        <input type="password" name="password" placeholder="${password}"/>
        <button type="submit">${loginButton}</button>
    </form>
</main>

</body>

</html>