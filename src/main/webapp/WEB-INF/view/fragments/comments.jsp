<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.comments" var="comments" />
<fmt:message bundle="${local}" key="local.placeholder.typeYourComment" var="typeYourComment" />
<fmt:message bundle="${local}" key="local.button.comment" var="buttonComment" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="comments-wrapper">

    <h1>${comments}</h1>
    <form action="${pageContext.request.contextPath}/controller?command=createComment" method="post" >
        <input type="text" name="newComment" placeholder="${typeYourComment}" />
        <button type="submit">${buttonComment}</button>
    </form>

    <c:forEach items="${sessionScope.selectedMusicComments}" var="comment">
        <h5>${comment.username}    ${comment.date}</h5>
        <p>${comment.comment}</p>
        <hr>
    </c:forEach>

</main>
</body>
</html>