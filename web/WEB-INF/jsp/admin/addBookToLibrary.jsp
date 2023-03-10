<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/10/2022
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../logout.jsp"%>
<form action="/addBookToLibrary" method="post" enctype="multipart/form-data">
  <label><fmt:message key="book.id"/> :
    <input type="number" name="bookId">
  </label>
  <label><fmt:message key="book.title"/>:
    <input type="text" name="bookTitle">
  </label>
  <label><fmt:message key="book.author"/>:
    <input type="text" name="bookAuthor">
  </label>
  <label><fmt:message key="book.year"/>:
    <input type="text" name="bookYear">
  </label>
  <label><fmt:message key="book.amount"/>:
    <input type="number" name="bookAmount">
  </label>
  <label><fmt:message key="book.genre"/>:
    <input type="text" name="genre">
  </label>
  <label><fmt:message key="book.cover"/>:
    <input type="file" name="cover" required>
  </label>
  <button type="submit"><fmt:message key="button.add"/></button>
</form>

</body>
</html>
