<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/23/2022
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form action="/locale" method="post">
    <button name="locale" value="uk_UA">UA</button>
    <button name="locale" value="en_US">US</button>
</form>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>