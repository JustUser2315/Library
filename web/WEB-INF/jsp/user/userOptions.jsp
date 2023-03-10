<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/12/2022
  Time: 5:11 PM
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
<%@ include file="../logout.jsp"%>
<form action="/userOptions" method="get">
    <a href="/seeAllBooks"><fmt:message key="admin.options.see.all.books"/></a><br>
    <a href="/seeMyReadingList"><fmt:message key="user.options.see.all.my.books"/></a><br>

</form>
</body>
</html>
