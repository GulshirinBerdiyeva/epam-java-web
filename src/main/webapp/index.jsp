<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.title.order.music" var="orderMusic" />
<fmt:message bundle="${local}" key="local.login" var="login" />
<fmt:message bundle="${local}" key="local.placeholder.username" var="username" />
<fmt:message bundle="${local}" key="local.placeholder.password" var="password" />
<fmt:message bundle="${local}" key="local.button.login" var="loginButton" />
<fmt:message bundle="${local}" key="local.error.message.errorLogin" var="errorLoginMessage" />


<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/styles.css" />
    <title>${orderMusic}</title>
</head>

<body class="index-body">

<header class="login-header">
    <div class="english">
        <form action="${pageContext.request.contextPath}/controller?command=english" method="post" >
            <button type="submit" />
        </form>
    </div>

    <div class="france">
        <form action="${pageContext.request.contextPath}/controller?command=france" method="post" >
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

        <c:if test="${requestScope.errorLogin != null}">
            <br/>
            <h2>${errorLoginMessage}</h2>
        </c:if>
        <br/>

        <input type="text" name="username" placeholder="${username}"/>
        <br/>
        <br/>
        <input type="password" name="password" placeholder="${password}"/>
        <button type="submit">${loginButton}</button>
    </form>
</main>

</body>

</html>