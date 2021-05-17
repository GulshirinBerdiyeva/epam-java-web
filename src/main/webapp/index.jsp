<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.title.order.music" var="orderMusic" />
<fmt:message bundle="${local}" key="local.login" var="login" />
<fmt:message bundle="${local}" key="local.placeholder.username" var="username" />
<fmt:message bundle="${local}" key="local.placeholder.password" var="password" />
<fmt:message bundle="${local}" key="local.button.logIn" var="buttonLogin"/>
<fmt:message bundle="${local}" key="local.error.message.errorLogin" var="errorLoginMessage" />
<fmt:message bundle="${local}" key="local.create.new.account" var="createNewAccount" />
<fmt:message bundle="${local}" key="local.button.signUp" var="buttonSignUp" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="static/styles.css" />
</head>

<body class="main-body">

<header>
    <jsp:include page="WEB-INF/view/fragments/header.jsp" />
</header>

<main class="login-form">
    <form action="${pageContext.request.contextPath}/controller?command=login" method="post" >
        <h1>${login}</h1>

        <c:if test="${requestScope.errorLogin}">
            <br/>
            <h2>${errorLoginMessage}</h2>
        </c:if>

        <br/>
        <input type="text" name="username" placeholder="${username}" />
        <br/>
        <br/>
        <input type="password" name="password" placeholder="${password}" />
        <button type="submit">${buttonLogin}</button>
        <div>
            <a href="${pageContext.request.contextPath}/controller?command=signUpPage" >${createNewAccount}</a>
        </div>
    </form>
</main>

</body>

</html>