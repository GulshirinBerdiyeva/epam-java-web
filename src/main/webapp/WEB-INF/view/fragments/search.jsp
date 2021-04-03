<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc" />

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

    <div class="search-wrapper">
        <form action="${pageContext.request.contextPath}/controller?command=searchMusic" method="post" >
            <input type="text" placeholder="Search here...">
            <button type="submit">Search</button>
        </form>
    </div>

    <div class="purchase-wrapper">
        <div class="purchase-image">
            <img src="${selectedMusic.imagePath}">
        </div>

        <div class="purchase-text">
            <h2>${selectedMusic.artist}</h2>
            <h2>${selectedMusic.title}</h2>
            <h2>$${selectedMusic.price}</h2>
        </div>

        <div class="buy-button">
            <form action="${pageContext.request.contextPath}/controller?command=buy" method="post" >
                <button type="submit">Buy</button>
            </form>
        </div>

        <c:if test="${buy != null}">
            <div class="order-confirmation">
                <form action="${pageContext.request.contextPath}/controller?command=confirm" method="post" >

                </form>
            </div>
        </c:if>

    </div>

</main>

</body>

</html>
