<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.login" var="login" />
<fmt:message bundle="${locale}" key="locale.placeholder.username" var="username" />
<fmt:message bundle="${locale}" key="locale.placeholder.password" var="password" />
<fmt:message bundle="${locale}" key="locale.button.logIn" var="buttonLogin"/>
<fmt:message bundle="${locale}" key="locale.error.message.errorLogin" var="errorLoginMessage" />
<fmt:message bundle="${locale}" key="locale.create.new.account" var="createNewAccount" />
<fmt:message bundle="${locale}" key="locale.title.name" var="titleName" />
<fmt:message bundle="${locale}" key="locale.title.password" var="titlePassword" />

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
        <input type="text" name="username" placeholder="${username}" min="2" max="61"
               pattern="[A-ZА-ЯЁ][a-zа-яё]{1,30}( [A-ZА-ЯЁ][a-zа-яё]{1,30})?"
               title="${titleName}" required />
        <br/>
        <br/>
        <input type="password" name="password" placeholder="${password}" min="8" max="20"
               pattern="(?=.*[A-ZА-ЯЁ])(?=.*[a-zа-яё])(?=.*\d)[\wА-Яа-яЁё]{8,20}"
               title="" required />
        <button type="submit">${buttonLogin}</button>
        <div>
            <a href="${pageContext.request.contextPath}/controller?command=signUpPage" >${createNewAccount}</a>
        </div>
    </form>
</main>

</body>

</html>