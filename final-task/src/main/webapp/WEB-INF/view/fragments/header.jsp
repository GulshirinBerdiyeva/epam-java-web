<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.title.order.music" var="orderMusic" />
<fmt:message bundle="${locale}" key="locale.heading.music" var="music" />
<fmt:message bundle="${locale}" key="locale.button.logout" var="buttonLogout"/>

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
    <title>${orderMusic}</title>
</head>

<body>

<div class="header-wrapper">
    <h1>${music}</h1>

    <c:if test="${sessionScope.user != null}" >
        <div class="logout">
            <form action="${pageContext.request.contextPath}/controller?command=logout" method="post" >
                <button type="submit">${buttonLogout}</button>
            </form>
        </div>
    </c:if>

    <div class="russian">
        <form action="${pageContext.request.contextPath}/controller?command=changeLanguage" method="post" >
            <button name="locale" value="ru" type="submit" />
        </form>
    </div>

    <div class="france">
        <form action="${pageContext.request.contextPath}/controller?command=changeLanguage" method="post" >
            <button  name="locale" value="fr" type="submit" />
        </form>
    </div>

    <div class="english">
        <form action="${pageContext.request.contextPath}/controller?command=changeLanguage" method="post" >
            <button name="locale" value="en" type="submit" />
        </form>
    </div>

</div>

</body>

</html>