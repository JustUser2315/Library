<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/21/2022
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>
<div id="UserDeleteBook">
  <c:if test="${not empty sessionScope.user}">
    <H1><fmt:message key="choose.book.title.delete"/>:/H1>
    <form action="/deleteMyBook" method="post">
      <select name="booksToDelete">
        <c:forEach var="b" items="${requestScope.books}">
          <option>${b.getTitle()}</option>
        </c:forEach>
      </select>
      <button type="submit"><fmt:message key="button.delete"/></button>
    </form>
  </c:if>
</div>


<div id="AdminDeleteBook">
  <c:if test="${not empty sessionScope.admin}">
    <h1><fmt:message key="choose.book.id.delete"/>:</h1>
    <form action="/deleteBook" method="post">
      <select name="bookId">
        <c:forEach var="b" items="${requestScope.books}">
          <option>${b.getId()}</option>
        </c:forEach>
      </select><br>
      <button type="submit"><fmt:message key="button.delete"/></button>
    </form>
  </c:if>
</div>
