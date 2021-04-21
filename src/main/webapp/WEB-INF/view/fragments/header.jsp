<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.title.order.music" var="orderMusic" />
<fmt:message bundle="${local}" key="local.heading.music" var="music" />
<fmt:message bundle="${local}" key="local.button.logout" var="buttonLogout"/>
<fmt:message bundle="${local}" key="local.button.signUp" var="buttonSignUp" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
    <title>${orderMusic}</title>
</head>

<body>

<div class="header-wrapper">
    <h1>${music}</h1>

    <div class="english">
        <form action="${pageContext.request.contextPath}/controller?command=changeLanguage" method="post" >
            <button name="language" value="english" type="submit" />
        </form>
    </div>

    <div class="france">
        <form action="${pageContext.request.contextPath}/controller?command=changeLanguage" method="post" >
            <button  name="language" value="france" type="submit" />
        </form>
    </div>

    <div class="russian">
        <form action="${pageContext.request.contextPath}/controller?command=changeLanguage" method="post" >
            <button name="language" value="russian" type="submit" />
        </form>
    </div>

    <c:if test="${sessionScope.user == null}" >
        <div class="signIn">
            <form action="${pageContext.request.contextPath}/controller?command=signUp" method="post" >
                <button type="submit">${buttonSignUp}</button>
            </form>
        </div>
    </c:if>

    <c:if test="${(sessionScope.user != null) || (sessionScope.currentPage eq '/WEB-INF/view/registration.jsp')}" >
        <div class="logout">
            <form action="${pageContext.request.contextPath}/controller?command=logout" method="post" >
                <button type="submit">${buttonLogout}</button>
            </form>
        </div>
    </c:if>
</div>

</body>

</html>
