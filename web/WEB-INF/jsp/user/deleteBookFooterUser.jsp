<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/13/2022
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="UserDeleteBook">
  <c:if test="${not empty sessionScope.user}">
    <H1><fmt:message key="choose.book.delete"/>:</H1>
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


