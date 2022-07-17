<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.placeholder.username" var="username" />
<fmt:message bundle="${locale}" key="locale.placeholder.password" var="password" />
<fmt:message bundle="${locale}" key="locale.signUp" var="signUp" />
<fmt:message bundle="${locale}" key="locale.button.signIn" var="buttonSignIn" />
<fmt:message bundle="${locale}" key="locale.error.message.errorSignIn" var="errorSignIn" />
<fmt:message bundle="${locale}" key="locale.error.message.enterInputParameters" var="enterInputParameters" />
<fmt:message bundle="${locale}" key="locale.login.page" var="loginPage" />
<fmt:message bundle="${locale}" key="locale.title.name" var="titleName" />
<fmt:message bundle="${locale}" key="locale.title.password" var="titlePassword" />
<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
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
        <input type="text" name="username" placeholder="${username}" min="2" max="61"
               pattern="[A-ZА-ЯЁ][a-zа-яё]{1,30}( [A-ZА-ЯЁ][a-zа-яё]{1,30})?"
               title="${titleName}" required />
        <br/>
        <br/>
        <input type="password" name="password" placeholder="${password}" min="8" max="20"
               pattern="(?=.*[A-ZА-ЯЁ])(?=.*[a-zа-яё])(?=.*\d)[\wА-Яа-яЁё]{8,20}"
               title="${titlePassword}" required />

        <button type="submit">${buttonSignIn}</button>

        <div>
            <a href="${pageContext.request.contextPath}/controller?command=logout" >${loginPage}</a>
        </div>
    </form>


</main>

</body>

</html>