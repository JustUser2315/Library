<%@ page import="serrvice.ImageService" %><%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/10/2022
  Time: 9:45 PM
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
<%@ include file="logout.jsp"%>
<h1><fmt:message key="books.list"/>:</h1>
<form action="/seeAllBooks" method="post">
    <span><fmt:message key="option.sort"/>:</span>
    <select name="sort">
        <option width="0%"><fmt:message key="book.id"/></option>
        <option><fmt:message key="book.title"/></option>
        <option><fmt:message key="book.author"/></option>
        <option><fmt:message key="book.year"/></option>
        <option><fmt:message key="book.amount"/></option>
        <option><fmt:message key="book.genre"/></option>
    </select>
<%--    <button type="submit">Search:</button>--%>
    <input type="search" name="search" placeholder="Search..."/>
    <button type="submit"><fmt:message key="button.sort.and.search"/></button><br>
    <button type="submit"><fmt:message key="button.reset.page"/></button>
  <table width="100%" border="1px solid white" bgcolor="#ffe4c4">
    <thead style="color: darkorange">
      <th width="0%"><fmt:message key="book.id"/></th>
      <th><fmt:message key="book.title"/></th>
      <th><fmt:message key="book.author"/></th>
      <th><fmt:message key="book.year"/></th>
      <th><fmt:message key="book.amount"/></th>
      <th><fmt:message key="book.genre"/></th>
    </thead>

    <c:forEach var="book" items="${requestScope.books}">
          <tbody>
          <td>${book.getId()}</td>
          <td>${book.getTitle()}</td>
          <td>${book.getAuthor()}</td>
          <td>${book.getYear()}</td>
          <td>${book.getAmount()}</td>
          <td>${book.getGenre()}</td>
          <td><img src="/images/${book.getCoverString()}" height="75" width="37.5"/></td>
          </tbody>
    </c:forEach>
  </table >
</form>
<%@include file="user/addBookToMyLibraryFooter.jsp"%>
<c:if test="${not empty sessionScope.admin}">
    <%@include file="deleteBookFooter.jsp"%>
</c:if>
</body>
</html>
