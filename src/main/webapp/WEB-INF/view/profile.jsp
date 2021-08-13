<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.profile.title.myProfile" var="myProfile" />
<fmt:message bundle="${locale}" key="locale.placeholder.fillBalance" var="fillBalance" />
<fmt:message bundle="${locale}" key="locale.profile.button.fillBalance" var="buttonFillBalance" />
<fmt:message bundle="${locale}" key="locale.error.message.invalidNumberFormat" var="invalidNumberFormat" />
<fmt:message bundle="${locale}" key="locale.currency.unit" var="currencyUnit" />
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
    <div class="profile-wrapper" >
        <h1>${myProfile}</h1>
        <hr/>

        <table id="profile" class="music-text-inform">
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=user.jpg" align="absmiddle" ></th>
                <th><h2><c:out value="${sessionScope.user.username}" /></h2></th>
            </tr>
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=purchases.jpg" align="absmiddle" ></th>
                <th><h2><c:out value="${sessionScope.user.musicAmount}" /></h2></th>
            </tr>
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=bonus.jpg" align="absmiddle" ></th>
                <th><h2><c:out value="${sessionScope.user.discount} %" /></h2></th>
            </tr>
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=cash.jpg" align="absmiddle" ></th>
                <th><h2><c:out value="${currencyUnit} ${requestScope.userCash}"/></h2></th>
            </tr>
        </table>

        <c:if test="${cookie.get('invalidNumberFormat').value != null}" >
            <br/>
            <h2 id="error-message-editPrice">${invalidNumberFormat}</h2>
        </c:if>

        <form action="${pageContext.request.contextPath}/controller?command=fillBalance" method="post" >
           <div class="profile-input-button-wrapper" >
               <input type="text" name="cashValue" placeholder="${fillBalance}" min="1" max="6"
                      pattern="(([1-9]|[1-9][0-9])(\.\d{1,2})?)|100(\.0{1,2})?"
                      title="${titleMoney}" required>
               <button type="submit">${buttonFillBalance}</button>
           </div>
        </form>

    </div>
</main>

</body>

</html>