<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.currency.unit" var="currencyUnit" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>
<main>
    <div class="purchase-image">
        <img src="${sessionScope.selectedMusic.imagePath}">
    </div>

    <div class="purchase-text">
        <h2>${sessionScope.selectedMusic.artist}</h2>
        <h2>${sessionScope.selectedMusic.title}</h2>
        <c:if test="${!sessionScope.musicOrder.payment}" >
            <h2>${currencyUnit} ${sessionScope.selectedMusicPrice}</h2>
        </c:if>
    </div>
</main>
</body>

</html>
