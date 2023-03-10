<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/18/2022
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>
<c:if test="${not empty sessionScope.admin}">
    <form action="/deleteUser" method="post">
        <label>Id:
            <input type="number" name="userIdToDelete"><br>
            <button type="submit"><fmt:message key="button.delete"/></button>
        </label>
    </form>
</c:if>
