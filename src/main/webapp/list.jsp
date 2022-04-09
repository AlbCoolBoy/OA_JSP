<%@ page import="java.util.List" %>
<%@ page import="com.example.OA_JSP.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title></title>
</head>
<body>


<h1 align='center'>部门列表</h1>
<%=session.getAttribute("Account")%>]
<a href="<%=request.getContextPath()%>/user/exit">退出系统</a>
<br>
<table border='1px' align='center'>
    <tr>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>部门位置</th>
        <th>操作</th>
    </tr>

    <%--这里先试用这种方式进行数据的拼接--%>
    <%
        Object deptlist = request.getAttribute("deptlist");
        List<Dept> list = (List) deptlist;
        for (Dept l : list) {
    %>
    <tr>
        <td><%=l.getNumber()%>
        </td>
        <td><%=l.getName()%>
        </td>
        <td><%=l.getLocation()%>
        </td>
        <td>
            <a href="/OA/dept/delete?number=<%=l.getNumber()%>">删除</a>
            <a href="/OA/dept/details?f=edit&number=<%=l.getNumber()%>">修改</a>
            <a href="/OA/dept/details?f=detail&number=<%=l.getNumber()%>">详情</a>
        </td>
    </tr>
    <%
        }
    %>
</table>


<a href='/OA/add.jsp'>添加数据</a>
</body>
</html>
