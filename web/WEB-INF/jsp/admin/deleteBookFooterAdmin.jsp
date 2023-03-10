<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/18/2022
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>
<c:if test="${not empty sessionScope.admin}">
  <form action="/deleteBook" method="post">
    <h1><fmt:message key="choose.book.title.delete"/>:</h1>
    <select name="bookId">
      <c:forEach var="b" items="${requestScope.books}">
        <option>${b.getId()}</option>
      </c:forEach>
    </select><br>
    <button type="submit"><fmt:message key="button.delete"/></button>
  </form>
</c:if>