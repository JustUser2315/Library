<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/11/2022
  Time: 3:35 PM
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
<%@include file="../logout.jsp"%>
<form action="/seeAllUsers" method="post">
    <h1><fmt:message key="admin.options.see.all.users"/>:</h1>
    <span><fmt:message key="option.sort"/>:</span>
    <select name="sort">
        <option><fmt:message key="user.id"/></option>
        <option><fmt:message key="user.name"/></option>
        <option><fmt:message key="user.birthday"/></option>
        <option><fmt:message key="user.email"/></option>
        <option><fmt:message key="user.password"/></option>
    </select>
    <input type="search" placeholder="Search..." name="search"/>
    <button type="submit"><fmt:message key="button.sort.and.search"/></button><br>
    <button type="submit"><fmt:message key="button.reset.page"/></button>
<table width="100%" bgcolor="aqua" border="1px solid white">
    <thead style="color: crimson">
    <th><fmt:message key="user.id"/></th>
    <th><fmt:message key="user.name"/></th>
    <th><fmt:message key="user.birthday"/></th>
    <th><fmt:message key="user.email"/></th>
    <th><fmt:message key="user.password"/></th>
    </thead>
    <c:forEach var="user" items="${requestScope.users}">
        <tbody>
        <td>${user.getId()}</td>
        <td>${user.getFirstName().concat(" ").concat(user.getLastName())}</td>
        <td>${user.getBirthday()}</td>
        <td>${user.getEmail()}</td>
        <td>${user.getPassword()}</td>
        <td><form action="/seeUsersBook" method="post">
            <button name="userId" value="${user.getId()}" type="submit"><fmt:message key="admin.options.see.user.library"/></button>
        </form> </td>
        </tbody>
    </c:forEach>
</table>
</form>
<%@include file="deleteUserFooter.jsp"%>
</body>
</html>
