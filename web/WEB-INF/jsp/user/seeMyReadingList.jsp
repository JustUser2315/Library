<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/12/2022
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../logout.jsp"%>
<form action="/seeMyReadingList" method="post">
    <c:if test="${not empty sessionScope.admin}">
        <h1><fmt:message key="user.books"/>:</h1>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <h1><fmt:message key="my.books"/>:</h1>
    </c:if>
    <span><fmt:message key="option.sort"/>:</span>
    <select name="sort">
        <option><fmt:message key="book.id"/></option>
        <option><fmt:message key="book.title"/></option>
        <option><fmt:message key="book.author"/></option>
        <option><fmt:message key="book.year"/></option>
        <option><fmt:message key="book.genre"/></option>
    </select>
    <input type="search" name="search" placeholder="Search...">
    <button type="submit"><fmt:message key="button.sort.and.search"/></button><br>
    <button type="submit"><fmt:message key="button.reset.page"/></button>
    <table width="100%" border="1 px solid white" bgcolor="#7fffd4">
        <thead style="color: crimson">
        <th><fmt:message key="book.id"/></th>
        <th><fmt:message key="book.title"/></th>
        <th><fmt:message key="book.author"/></th>
        <th><fmt:message key="book.year"/></th>
        <th><fmt:message key="book.genre"/></th>
        </thead>
        <c:forEach var="b" items="${requestScope.books}">
        <tbody>
            <td>${b.getId()}</td>
            <td>${b.getTitle()}</td>
            <td>${b.getAuthor()}</td>
            <td>${b.getYear()}</td>
            <td>${b.getGenre()}</td>
            <td><img src="images/${b.getCoverString()}" width="75" height="37.5"></td>
        </tbody>
        </c:forEach>
    </table>
</form>
<%@include file="../deleteBookFooter.jsp"%>



<%--<H1>Choose to delete:</H1>--%>
<%--<form action="/deleteMyBook" method="post">--%>
<%--    <select name="booksToDelete">--%>
<%--        <c:forEach var="b" items="${requestScope.books}">--%>
<%--            <option>${b.getTitle()}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <button type="submit">Delete</button>--%>
<%--</form>--%>
</body>
</html>
