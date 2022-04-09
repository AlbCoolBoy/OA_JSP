<%@ page import="java.util.Map" %>
<%@ page import="com.example.OA_JSP.User" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ALB
  Date: 2022-04-08
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
    Map<String, User> usermap=new HashMap<String, User>();
    User user=new User();
    user.setUsername("justin");
    usermap.put("user",user);
    request.setAttribute("userrequest",usermap);
%>

${userrequest.user.username}

