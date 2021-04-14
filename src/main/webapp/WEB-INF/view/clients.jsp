<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.profile.id" var="id" />
<fmt:message bundle="${local}" key="local.profile.username" var="username" />
<fmt:message bundle="${local}" key="local.profile.musicAmount" var="musicAmount" />
<fmt:message bundle="${local}" key="local.profile.discount" var="discount" />
<fmt:message bundle="${local}" key="local.profile.bonus" var="bonus" />
<fmt:message bundle="${local}" key="local.placeholder.discountValue" var="discountValue" />
<fmt:message bundle="${local}" key="local.button.apply" var="buttonApply" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="main-body">

<header>
    <jsp:include page="fragments/header.jsp" />
</header>

<nav>
    <jsp:include page="fragments/menu.jsp" />
</nav>

<main>
    <table id="table" >
        <thead>
        <tr>
            <th>№</th>
            <th>${id}</th>
            <th>${username}</th>
            <th>${musicAmount}</th>
            <th>${discount}</th>
            <th>${bonus}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.clients}" var="client" varStatus="i">
            <tr>
                <th>${i.index+1}</th>
                <th>${client.id}</th>
                <th>${client.username}</th>
                <th>${client.musicAmount}</th>
                <th>${client.discount}</th>
                <th>
                    <form action="${pageContext.request.contextPath}/controller?command=applyDiscount" method="post" >
                        <input type="text" name="discountValue" placeholder="${discountValue}" />
                        <button name="clientId" value="${client.id}" type="submit">${buttonApply}</button>
                    </form>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

</body>

</html>

