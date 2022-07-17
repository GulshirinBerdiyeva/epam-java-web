<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.epam.task.web.project.entity.Role" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.button.edit" var="buttonEdit" />
<fmt:message bundle="${locale}" key="locale.button.buy" var="buttonBuy" />
<fmt:message bundle="${locale}" key="locale.error.message.existInPlaylist" var="existInPlaylist" />
<fmt:message bundle="${locale}" key="locale.error.message.notEnoughMoney" var="notEnoughMoney" />
<fmt:message bundle="${locale}" key="locale.currency.unit" var="currencyUnit" />
<fmt:message bundle="${locale}" key="locale.button.confirm" var="buttonConfirm" />
<fmt:message bundle="${locale}" key="locale.button.cancel" var="buttonCancel" />
<fmt:message bundle="${locale}" key="locale.placeholder.price" var="price" />
<fmt:message bundle="${locale}" key="locale.button.editPrice" var="buttonEditPrice" />
<fmt:message bundle="${locale}" key="locale.button.delete" var="buttonDelete" />
<fmt:message bundle="${locale}" key="locale.error.message.invalidNumberFormat" var="invalidNumberFormat" />
<fmt:message bundle="${locale}" key="locale.delete.dialog.theme" var="deleteDialogTheme" />
<fmt:message bundle="${locale}" key="locale.delete.dialog.body" var="deleteDialogBody" />
<fmt:message bundle="${locale}" key="locale.delete.dialog.button.cancel" var="deleteDialogButtonCancel" />
<fmt:message bundle="${locale}" key="locale.delete.dialog.button.delete" var="deleteDialogButtonDelete" />
<fmt:message bundle="${locale}" key="locale.title.money" var="titleMoney" />

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
    <c:if test="${requestScope.music != null}" >
        <div class="purchase-wrapper" >
            <div id="purchase" >
                <jsp:include page="fragments/perform.jsp" />
            </div>

            <div id="control" >
                <div class="purchase-buttons">

                    <c:if test="${Role.ADMIN.equals(sessionScope.user.role)}" >
                        <br/>
                        <audio controls>
                            <source src="${pageContext.request.contextPath}/controller?command=getResource&type=music&fileName=${requestScope.music.audioFileName}" type="audio/mpeg">
                        </audio>

                        <c:if test="${cookie.get('invalidNumberFormat') != null}" >
                            <br/>
                            <h2 id="error-message-editPrice">${invalidNumberFormat}</h2>
                        </c:if>

                        <div class="editPrice-delete-wrapper">
                            <form action="${pageContext.request.contextPath}/controller?command=editPrice" method="post" >
                                <div class="edit-price" >
                                    <input type="text" name="newPrice" placeholder="${price}" min="1" max="6"
                                           pattern="(([1-9]|[1-9][0-9])(\.\d{1,2})?)|100(\.0{1,2})?"
                                           title="${titleMoney}" required>
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


                    <c:if test="${Role.CLIENT.equals(sessionScope.user.role)}" >

                        <c:if test="${!requestScope.payed && !requestScope.canBuy}" >
                            <form action="${pageContext.request.contextPath}/controller?command=buy" method="post" >
                                <button type="submit">${buttonBuy}</button>
                            </form>
                        </c:if>

                        <c:if test="${requestScope.payed}">
                            <br/>
                            <audio controls>
                                <source src="${pageContext.request.contextPath}/controller?command=getResource&type=music&fileName=${requestScope.music.audioFileName}" type="audio/mpeg">
                            </audio>
                        </c:if>

                        <c:if test="${cookie.get('existInPlaylist').value != null}" >
                            <br/>
                            <h2 id="error-message-editPrice">${existInPlaylist}</h2>
                        </c:if>

                        <c:if test="${cookie.get('notEnoughMoney').value != null}" >
                            <br/>
                            <h2 id="error-message-editPrice">${notEnoughMoney}</h2>
                        </c:if>

                        <c:if test="${requestScope.canBuy}">
                            <br/>
                            <div class="purchase-table">
                                <table class="music-text-inform">
                                    <tr>
                                        <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=discount.jpg" align="absmiddle" ></th>
                                        <th><h2><c:out value="${requestScope.discount} %" /></h2></th>
                                    </tr>
                                    <tr>
                                        <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=cost.jpg" align="absmiddle" ></th>
                                        <th><h2><c:out value="${currencyUnit} ${requestScope.finalPrice}" /></h2></th>
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
                    </c:if>
                </div>
            </div>

            <c:if test="${cookie.get('canDelete').value != null}" >
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