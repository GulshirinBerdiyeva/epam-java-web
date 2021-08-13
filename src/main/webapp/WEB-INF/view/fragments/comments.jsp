<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale" />

<fmt:message bundle="${locale}" key="locale.button.comments" var="comments" />
<fmt:message bundle="${locale}" key="locale.placeholder.typeYourComment" var="typeYourComment" />
<fmt:message bundle="${locale}" key="locale.button.comment" var="buttonComment" />
<fmt:message bundle="${locale}" key="locale.error.message.enterInputParameters" var="enterInputParameters" />
<fmt:message bundle="${locale}" key="locale.title.comment" var="commentTitle" />

<html>

<head>
    <meta name="viewport" content="width-device-width, initial-scale-1.0" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/styles.css" />
</head>

<body>

<main class="comments-wrapper">
    <h1>${comments}</h1>

    <c:if test="${cookie.get('emptyInputParameters').value != null}" >
        <br/>
        <h2 id="error-message-editPrice">${enterInputParameters}</h2>
    </c:if>

    <br/>
    <form action="${pageContext.request.contextPath}/controller?command=createComment" method="post" >
        <input type="text" name="newComment" placeholder="${typeYourComment}" min="1" max="100"
               pattern="(?=.*[A-ZА-ЯЁa-zа-яё0-9]).{1,100}"
               title="${commentTitle}" required/>
        <button type="submit">${buttonComment}</button>
    </form>


    <div class="comments" >
        <c:if test="${!empty requestScope.musicComments}" >
            <c:forEach items="${requestScope.musicComments}" var="comment">
                <br/>
                <h5><c:out value="${comment.localeDateTime}" /></h5>
                <h4><c:out value="${comment.username.toUpperCase()}:" /></h4>
                <h2><c:out value="${comment.comment}" /></h2>
                <hr/>
            </c:forEach>
        </c:if>
    </div>
</main>

</body>

</html>