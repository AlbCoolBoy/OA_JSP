<%@ page import="java.util.List" %>
<%@ page import="com.example.OA_JSP.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title></title>
</head>
<body>


<h1 align='center'>部门列表</h1>
<%=session.getAttribute("Account")%>]
<a href="${pageContext.request.contextPath}/user/exit">退出系统</a>
<br>
<table border='1px' align='center'>
    <tr>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>部门位置</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${deptlist}" var="dept">
        <tr>
            <td>${dept.number}
            </td>
            <td>${dept.name}
            </td>
            <td>${dept.location}
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/dept/delete?number=${dept.number}">删除</a>
                <a href="${pageContext.request.contextPath}/dept/details?f=edit&number=${dept.name}">修改</a>
                <a href="${pageContext.request.contextPath}/dept/details?f=detail&number=${dept.location}">详情</a>
            </td>
        </tr>
    </c:forEach>
</table>


<a href='${pageContext.request.contextPath}/add.jsp'>添加数据</a>
</body>
</html>
