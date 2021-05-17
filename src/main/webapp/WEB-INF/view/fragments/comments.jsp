<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="local" />

<fmt:message bundle="${local}" key="local.button.comments" var="comments" />
<fmt:message bundle="${local}" key="local.placeholder.typeYourComment" var="typeYourComment" />
<fmt:message bundle="${local}" key="local.button.comment" var="buttonComment" />
<fmt:message bundle="${local}" key="local.error.message.enterInputParameters" var="enterInputParameters" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="comments-wrapper">
    <h1>${comments}</h1>

    <c:if test="${requestScope.emptyInputParameters}" >
        <br/>
        <h2 id="error-message-editPrice">${enterInputParameters}</h2>
    </c:if>

    <br/>
    <form action="${pageContext.request.contextPath}/controller?command=createComment" method="post" >
        <input type="text" name="newComment" placeholder="${typeYourComment}" />
        <button type="submit">${buttonComment}</button>
    </form>


    <div class="comments" >
        <c:forEach items="${sessionScope.selectedMusicComments}" var="comment">
            <br/>
            <h5>${comment.localeDateTime}</h5>
            <h4>${comment.username.toUpperCase()}:</h4>
            <h2>${comment.comment}</h2>
            <hr/>
        </c:forEach>
    </div>
</main>

</body>

</html>