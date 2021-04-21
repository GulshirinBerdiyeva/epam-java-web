<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.profile.title.myProfile" var="myProfile" />
<fmt:message bundle="${local}" key="local.profile.username" var="username" />
<fmt:message bundle="${local}" key="local.profile.musicAmount" var="musicAmount" />
<fmt:message bundle="${local}" key="local.profile.bonus" var="bonus" />
<fmt:message bundle="${local}" key="local.profile.balance" var="balance" />
<fmt:message bundle="${local}" key="local.placeholder.amount" var="amount" />
<fmt:message bundle="${local}" key="local.profile.fillBalance" var="fillBalance" />
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

<main>
    <div class="profile-wrapper" >
        <h1>${myProfile}</h1>
        <hr/>

        <div class="profile-left" >
            <h2>${username}:</h2>

            <h2>${musicAmount}:</h2>

            <h2>${bonus}:</h2>

            <h2>${balance}:</h2>
        </div>

        <div class="profile-right" >
            <h3>${sessionScope.user.username}</h3>

            <h3>${sessionScope.user.musicAmount}</h3>

            <h3>${sessionScope.user.discount}</h3>

            <h3>${sessionScope.user.cash}</h3>
        </div>

        <c:if test="${requestScope.invalidNumberFormat}" >
            <br/>
            <h2 id="error-message-editPrice">${invalidNumberFormat}</h2>
        </c:if>

        <form action="${pageContext.request.contextPath}/controller?command=fillBalance" method="post" >
           <div class="profile-input-button-wrapper" >
               <input type="text" name="cashValue" placeholder="${amount}" />
               <button type="submit">${fillBalance}</button>
           </div>
        </form>

    </div>

</main>

</body>

</html>

