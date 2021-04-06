<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.buy" var="buttonBuy" />
<fmt:message bundle="${local}" key="local.error.existInPlaylist" var="existInPlaylist" />
<fmt:message bundle="${local}" key="local.error.notEnoughMoney" var="notEnoughMoney" />
<fmt:message bundle="${local}" key="local.discount" var="discount" />
<fmt:message bundle="${local}" key="local.finalPrice" var="finalPrice" />
<fmt:message bundle="${local}" key="local.currency.unit" var="currencyUnit" />
<fmt:message bundle="${local}" key="local.button.confirm" var="buttonConfirm" />
<fmt:message bundle="${local}" key="local.button.cancel" var="buttonCancel" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body class="search-body">

<nav>
    <jsp:include page="header.jsp" />
</nav>
<nav>
    <jsp:include page="menu.jsp" />
</nav>

<main class="search-main">

    <div class="purchase-wrapper">

       <jsp:include page="perform.jsp" />

        <div class="buy-button">
            <form action="${pageContext.request.contextPath}/controller?command=buy" method="post" >
                <button type="submit">${buttonBuy}</button>
            </form>
        </div>

        <c:if test="${requestScope.existInPlaylist}" >
            <br/>
            <h2>${existInPlaylist}</h2>
        </c:if>

        <c:if test="${requestScope.notEnoughMoney}" >
            <br/>
            <h2>${notEnoughMoney}</h2>
        </c:if>

        <c:if test="${requestScope.canBuy}">

            <h2>${discount}: ${sessionScope.musicOrder.discount}%</h2>
            <h2>${finalPrice}: ${currencyUnit} ${sessionScope.musicOrder.finalPrice}</h2>

            <div class="order-confirmation">
                <form action="${pageContext.request.contextPath}/controller?command=confirm" method="post" >
                    <button id="confirm" type="submit">${buttonConfirm}</button>
                </form>
            </div>

            <div class="order-rejection">
                <form action="${pageContext.request.contextPath}/controller?command=cancel" method="post" >
                    <button id="cancel" type="submit">${buttonCancel}</button>
                </form>
            </div>

        </c:if>

    </div>

</main>

</body>

</html>
