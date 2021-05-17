<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.menu.clients" var="clients" />
<fmt:message bundle="${local}" key="local.profile.username" var="username" />
<fmt:message bundle="${local}" key="local.profile.musicAmount" var="musicAmount" />
<fmt:message bundle="${local}" key="local.profile.discount" var="discount" />
<fmt:message bundle="${local}" key="local.profile.bonus" var="bonus" />
<fmt:message bundle="${local}" key="local.placeholder.discountValue" var="discountValue" />
<fmt:message bundle="${local}" key="local.button.apply" var="buttonApply" />
<fmt:message bundle="${local}" key="local.error.message.invalidNumberFormat" var="invalidNumberFormat" />

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

<main class="album-main">
    <div class="clients-header" >
        <h1>${clients}</h1>

        <c:if test="${requestScope.invalidNumberFormat}" >
            <br/>
            <h2>${invalidNumberFormat}</h2>
        </c:if>
    </div>

    <div class="table-wrapper" >
        <table class="table" >
            <thead id="table-thead">
            <tr>
                <th id="thead-number">№</th>
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
                    <th>${client.username}</th>
                    <th>${client.musicAmount}</th>
                    <th>${client.discount} %</th>
                    <th id="table-column">
                        <form action="${pageContext.request.contextPath}/controller?command=applyDiscount" method="post" >
                            <div class="discount-wrapper-input-button" >
                                <input type="text" name="discountValue" placeholder="${discountValue}" />
                                <button name="clientId" value="${client.id}" type="submit">${buttonApply}</button>
                            </div>
                        </form>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

</body>

</html>