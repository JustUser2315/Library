<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/21/2022
  Time: 7:03 PM
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
<%--<%@include file="locale.jsp"%>--%>
<form action="/login" method="post">
  <label><fmt:message key="email"/>:
    <input type="email" name="Email"><br>
  </label>
  <label><fmt:message key="password"/>:
    <input type="password" name="Password"><br>
  </label>
  <button type="submit" name="role" value="Admin"><fmt:message key="button.submit.admin"/></button>
  <button type="submit" name="role" value="User"><fmt:message key="button.submit.user"/></button>
</form>
<c:if test="${not empty requestScope.valid}">
  <c:forEach var="v" items="${requestScope.valid}">
    <span style="color: red">${v.getMessage()}</span><br>
  </c:forEach>
</c:if>
</body>
</html>
