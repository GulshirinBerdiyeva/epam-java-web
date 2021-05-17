<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.edit" var="buttonEdit" />
<fmt:message bundle="${local}" key="local.button.buy" var="buttonBuy" />
<fmt:message bundle="${local}" key="local.error.message.existInPlaylist" var="existInPlaylist" />
<fmt:message bundle="${local}" key="local.error.message.notEnoughMoney" var="notEnoughMoney" />
<fmt:message bundle="${local}" key="local.error.message.paid" var="paid" />
<fmt:message bundle="${local}" key="local.currency.unit" var="currencyUnit" />
<fmt:message bundle="${local}" key="local.button.confirm" var="buttonConfirm" />
<fmt:message bundle="${local}" key="local.button.cancel" var="buttonCancel" />
<fmt:message bundle="${local}" key="local.placeholder.price" var="price" />
<fmt:message bundle="${local}" key="local.button.editPrice" var="buttonEditPrice" />
<fmt:message bundle="${local}" key="local.button.delete" var="buttonDelete" />
<fmt:message bundle="${local}" key="local.error.message.invalidNumberFormat" var="invalidNumberFormat" />
<fmt:message bundle="${local}" key="local.delete.dialog.theme" var="deleteDialogTheme" />
<fmt:message bundle="${local}" key="local.delete.dialog.body" var="deleteDialogBody" />
<fmt:message bundle="${local}" key="local.delete.dialog.button.cancel" var="deleteDialogButtonCancel" />
<fmt:message bundle="${local}" key="local.delete.dialog.button.delete" var="deleteDialogButtonDelete" />

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
    <c:if test="${sessionScope.selectedMusic != null}" >
        <div class="purchase-wrapper" >
            <div id="purchase" >
                <jsp:include page="fragments/perform.jsp" />
            </div>
            <div id="control" >
                <div class="purchase-buttons">
                    <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}" >
                        <br/>
                        <audio controls>
                            <source src="${pageContext.request.contextPath}/controller?command=getResource&type=music&fileName=${sessionScope.selectedMusic.audioFileName}" type="audio/mpeg">
                        </audio>

                        <form action="${pageContext.request.contextPath}/controller?command=editMusic" method="post" >
                            <button type="submit">${buttonEdit}</button>
                        </form>

                        <c:if test="${requestScope.invalidNumberFormat}" >
                            <br/>
                            <h2 id="error-message-editPrice">${invalidNumberFormat}</h2>
                        </c:if>

                        <c:if test="${requestScope.canEdit}" >
                            <div class="editPrice-delete-wrapper">
                                <form action="${pageContext.request.contextPath}/controller?command=editPrice" method="post" >
                                    <div class="edit-price" >
                                        <input type="text" name="newPrice" placeholder="${price}">
                                        <button id="edit-price" type="submit">${buttonEditPrice}</button>
                                    </div>
                                </form>

                                <form action="${pageContext.request.contextPath}/controller?command=deleteMusic&deleteDialog=true" method="post" >
                                    <div class="delete-wrapper" >
                                        <button id="delete" type="submit">${buttonDelete}</button>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </c:if>

                    <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}" >
                        <c:if test="${!requestScope.payed}" >
                            <form action="${pageContext.request.contextPath}/controller?command=buy" method="post" >
                                <button type="submit">${buttonBuy}</button>
                            </form>
                        </c:if>

                        <c:if test="${requestScope.existInPlaylist}" >
                            <br/>
                            <h2 id="error-message-editPrice">${existInPlaylist}</h2>
                        </c:if>

                        <c:if test="${requestScope.notEnoughMoney}" >
                            <br/>
                            <h2 id="error-message-editPrice">${notEnoughMoney}</h2>
                        </c:if>

                        <c:if test="${requestScope.paid}" >
                            <br/>
                            <h2 id="error-message-editPrice">${paid}</h2>
                        </c:if>

                        <c:if test="${requestScope.canBuy}">
                            <br/>
                            <div class="purchase-table">
                                <table class="music-text-inform">
                                    <tr>
                                        <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=discount.jpg" align="absmiddle" ></th>
                                        <th><h2>${sessionScope.musicOrder.discount}</h2></th>
                                    </tr>
                                    <tr>
                                        <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=cost.jpg" align="absmiddle" ></th>
                                        <th><h2>${currencyUnit} ${sessionScope.finalPrice}</h2></th>
                                    </tr>
                                </table>
                            </div>

                            <div class="confirm-cancel">
                                <form action="${pageContext.request.contextPath}/controller?command=confirmPurchase" method="post" >
                                    <button id="confirm" type="submit">${buttonConfirm}</button>
                                </form>

                                <form action="${pageContext.request.contextPath}/controller?command=cancelPurchase" method="post" >
                                    <button id="cancel" type="submit">${buttonCancel}</button>
                                </form>
                            </div>
                        </c:if>

                        <c:if test="${requestScope.payed}">
                            <br/>
                            <audio controls>
                                <source src="${pageContext.request.contextPath}/controller?command=getResource&type=music&fileName=${sessionScope.selectedMusic.audioFileName}" type="audio/mpeg">
                            </audio>
                        </c:if>

                    </c:if>
                </div>
            </div>

            <c:if test="${requestScope.canDelete}" >
                <div class="delete-dialog">
                    <h1>${deleteDialogTheme}</h1>
                    <br/>
                    <p>${deleteDialogBody}</p>
                    <br/>
                    <form action="${pageContext.request.contextPath}/controller?command=confirmDelete&delete=false" method="post">
                        <button id="cancel-delete" type="submit">${deleteDialogButtonCancel}</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/controller?command=cancelDelete&delete=true" method="post">
                        <button id="confirm-delete" type="submit">${deleteDialogButtonDelete}</button>
                    </form>
                </div>
            </c:if>

            <div id="comment" >
                <jsp:include page="fragments/comments.jsp" />
            </div>
        </div>
    </c:if>
</main>

</body>

</html>