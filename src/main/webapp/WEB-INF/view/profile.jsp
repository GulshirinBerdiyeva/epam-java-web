<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.profile.title.myProfile" var="myProfile" />
<fmt:message bundle="${local}" key="local.placeholder.fillBalance" var="fillBalance" />
<fmt:message bundle="${local}" key="local.profile.button.fillBalance" var="buttonFillBalance" />
<fmt:message bundle="${local}" key="local.error.message.invalidNumberFormat" var="invalidNumberFormat" />
<fmt:message bundle="${local}" key="local.currency.unit" var="currencyUnit" />

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
                <th><h2>${sessionScope.user.username}</h2></th>
            </tr>
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=purchases.jpg" align="absmiddle" ></th>
                <th><h2>${sessionScope.user.musicAmount}</h2></th>
            </tr>
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=bonus.jpg" align="absmiddle" ></th>
                <th><h2>${sessionScope.user.discount} %</h2></th>
            </tr>
            <tr>
                <th><img src="${pageContext.request.contextPath}/controller?command=getResource&type=icon&fileName=cash.jpg" align="absmiddle" ></th>
                <th><h2>${currencyUnit} ${sessionScope.userCash}</h2></th>
            </tr>
        </table>

        <c:if test="${requestScope.invalidNumberFormat}" >
            <br/>
            <h2 id="error-message-editPrice">${invalidNumberFormat}</h2>
        </c:if>

        <form action="${pageContext.request.contextPath}/controller?command=fillBalance" method="post" >
           <div class="profile-input-button-wrapper" >
               <input type="text" name="cashValue" placeholder="${fillBalance}" />
               <button type="submit">${buttonFillBalance}</button>
           </div>
        </form>

    </div>
</main>

</body>

</html>