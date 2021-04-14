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
<fmt:message bundle="${local}" key="local.placeholder.topUpAmount" var="topUpAmount" />
<fmt:message bundle="${local}" key="local.profile.topUpBalance" var="topUpBalance" />

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

        <form action="${pageContext.request.contextPath}/controller?command=topUpBalance" method="post" >
           <div class="profile-input-button-wrapper" >
               <input type="text" name="cashValue" placeholder="${topUpBalance}" />
               <button type="submit">${topUpBalance}</button>
           </div>
        </form>

    </div>

</main>

</body>

</html>

