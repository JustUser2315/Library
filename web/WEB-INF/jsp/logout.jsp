<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/21/2022
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>

<div id="AdminLogout">
    <c:if test="${not empty sessionScope.admin}">
        <form action="/logout" method="post">
            <button type="submit"><fmt:message key="button.logout"/></button>
        </form>
    </c:if>
</div>
<div id="UserLogout">
    <c:if test="${not empty sessionScope.user}">
        <form action="/logout" method="post">
            <button type="submit"><fmt:message key="button.logout"/></button>
        </form>
    </c:if>
</div>
