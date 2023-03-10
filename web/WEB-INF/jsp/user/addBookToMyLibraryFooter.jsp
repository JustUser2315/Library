<%--
  Created by IntelliJ IDEA.
  User: vende
  Date: 10/13/2022
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations"/>

<c:if test="${not empty sessionScope.user}">
    <form action="<c:url value="/addBookToOwnLibrary"/>" method="post">
        <h1><fmt:message key="choose.book.add"/>:</h1>
        <label>
            <select name="book">
                <c:forEach var="b" items="${requestScope.books}">
                    <option>${b.getTitle()}</option>
                </c:forEach>
            </select>
        </label><br>
        <button type="submit"><fmt:message key="button.add"/></button><br>
    </form>
</c:if>

