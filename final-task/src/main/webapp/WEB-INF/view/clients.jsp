<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.menu.clients" var="clients" />
<fmt:message bundle="${locale}" key="locale.profile.username" var="username" />
<fmt:message bundle="${locale}" key="locale.profile.musicAmount" var="musicAmount" />
<fmt:message bundle="${locale}" key="locale.profile.discount" var="discount" />
<fmt:message bundle="${locale}" key="locale.profile.bonus" var="bonus" />
<fmt:message bundle="${locale}" key="locale.placeholder.discountValue" var="discountValue" />
<fmt:message bundle="${locale}" key="locale.button.apply" var="buttonApply" />
<fmt:message bundle="${locale}" key="locale.error.message.invalidNumberFormat" var="invalidNumberFormat" />
<fmt:message bundle="${locale}" key="locale.no.clients" var="noClients" />
<fmt:message bundle="${locale}" key="locale.title.discount" var="titleDiscount" />

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

        <c:if test="${cookie.get('invalidNumberFormat').value != null}" >
            <br/>
            <h2>${invalidNumberFormat}</h2>
        </c:if>
    </div>

    <div class="table-wrapper" >
        <c:if test="${requestScope.noClients}" >
            <br/>
            <h2 id="error-message-editPrice">${noClients}</h2>
        </c:if>

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
            <c:if test="${!empty requestScope.clients}" >
                <c:forEach items="${requestScope.clients}" var="client" varStatus="i">
                    <tr>
                        <th><c:out value="${i.index+1}" /></th>
                        <th><c:out value="${client.username}" /></th>
                        <th><c:out value="${client.musicAmount}" /></th>
                        <th><c:out value="${client.discount} %" /></th>
                        <th id="table-column">
                            <form action="${pageContext.request.contextPath}/controller?command=applyDiscount" method="post" >
                                <div class="discount-wrapper-input-button" >
                                    <input type="text" name="discountValue" placeholder="${discountValue}" min="1" max="3"
                                           pattern="[0-9]|[1-9][0-9]|100"
                                           title="${titleDiscount}" required/>
                                    <button name="clientId" value="${client.id}" type="submit">${buttonApply}</button>
                                </div>
                            </form>
                        </th>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</main>

</body>

</html>