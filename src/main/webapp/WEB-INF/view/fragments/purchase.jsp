<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.edit" var="buttonEdit" />
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

<body class="purchase-body">

<nav>
    <jsp:include page="header.jsp" />
</nav>
<nav>
    <jsp:include page="menu.jsp" />
</nav>

<main>
    <table class="purchase-table">
        <tr>
            <th id="comment"><jsp:include page="comments.jsp" /></th>
            <th id="purchase"><jsp:include page="perform.jsp" /></th>
            <th id="control">
                <div class="purchase-buttons">
                    <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}" >
                        <audio controls controlsList="nodownload">
                            <source src="${sessionScope.selectedMusic.audioPath}" type="audio/mpeg">
                        </audio>

                        <form action="${pageContext.request.contextPath}/controller?command=edit" method="post" >
                            <button type="submit">${buttonEdit}</button>
                        </form>
                    </c:if>

                    <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}" >
                        <c:if test="${!requestScope.payed}" >
                            <form action="${pageContext.request.contextPath}/controller?command=buy" method="post" >
                                <button type="submit">${buttonBuy}</button>
                            </form>
                        </c:if>

                        <c:if test="${requestScope.payed}">
                            <audio controls controlsList="nodownload">
                                <source src="${sessionScope.selectedMusic.audioPath}" type="audio/mpeg">
                            </audio>
                        </c:if>

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

                            <form action="${pageContext.request.contextPath}/controller?command=confirmPurchase" method="post" >
                                <button id="confirm" type="submit">${buttonConfirm}</button>
                            </form>

                            <form action="${pageContext.request.contextPath}/controller?command=cancelPurchase" method="post" >
                                <button id="cancel" type="submit">${buttonCancel}</button>
                            </form>
                        </c:if>
                    </c:if>

                </div>

            </th>
        </tr>
    </table>

</main>

</body>

</html>
