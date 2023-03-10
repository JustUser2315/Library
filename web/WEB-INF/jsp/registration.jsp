<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/11/2022
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
  <label><fmt:message key="first.name"/> :
    <input type="text" name="FirstName">
  </label><br>
  <label><fmt:message key="last.name"/>:
    <input type="text" name="LastName">
  </label><br>
  <label><fmt:message key="birthday"/>:
    <input type="date" name="Birthday">
  </label><br>
  <label><fmt:message key="email"/>:
    <input type="email" name="Email">
  </label><br>
  <label><fmt:message key="password"/>:
    <input type="password" name="Password">
  </label><br>
<label><fmt:message key="role"/>:
    <c:forEach var="role" items="${requestScope.roles}">
      <span style="color: cornflowerblue">${role}</span>
      <input type="radio" name="role" value="${role}">
    </c:forEach><br>
</label><br>
  <button type="submit"><fmt:message key="button.registration"/></button>
</form>
<c:if test="${not empty requestScope.valid}">
  <c:forEach var="v" items="${requestScope.valid}">
    <span style="color: red">${v.getMessage()}</span><br>
  </c:forEach>
</c:if>
</body>
</html>
