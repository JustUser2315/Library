<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/10/2022
  Time: 7:16 PM
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
<%--<jsp:include page="logoutHeaderAdmin.jsp"/>--%>
<%@include file="../logout.jsp"%>
<form action="/adminOptions" method="get">
    <a href="/addBookToLibrary"><fmt:message key="admin.options.add.book"/></a><br>
    <a href="/seeAllBooks"><fmt:message key="admin.options.see.all.books"/></a><br>
    <a href="/seeAllUsers"><fmt:message key="admin.options.see.all.users"/></a><br>
</form>

</body>
</html>
