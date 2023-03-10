<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/24/2022
  Time: 3:14 PM
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
<%@include file="locale.jsp"%>
<form action="/startPage" method="get">
    <a href="/login"><fmt:message key="page.login"/></a><br>
    <a href="/registration"><fmt:message key="page.registration"/></a><br>
</form>
</body>
</html>
