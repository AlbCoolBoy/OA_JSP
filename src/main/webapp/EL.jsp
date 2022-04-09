<%@ page import="com.example.OA_JSP.User" %><%--
  Created by IntelliJ IDEA.
  User: ALB
  Date: 2022-04-07
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
    User user=new User();
    user.setUsername("justin");
    user.setAge(12);
    user.setPassword("123456");

    request.setAttribute("user", user);

    session.setAttribute("user",user);
    application.setAttribute("user",user);
    pageContext.setAttribute("user",user);
%>
${pageScope.user}
${requestScope.user}
${sessionScope.user}
${applicationsScope.user}


${user}
<br>
${user.username}
${user.password}


