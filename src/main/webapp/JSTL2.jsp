<%--
  Created by IntelliJ IDEA.
  User: ALB
  Date: 2022-04-09
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--if标签，test属性支持el表达式--%>
<c:if test="${empty param.username}">

</c:if>


<c:forEach var="i" begin="1" end="10" step="1">
    ${i} <br>
</c:forEach>

<c:choose>
    <c:when test="">

    </c:when>

    <c:when test="">

    </c:when>
    <c:when test="">

    </c:when>

</c:choose>