<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ALB
  Date: 2022-04-08
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<String> list=new ArrayList<>();
    list.add("abc");
    list.add("def");

    request.setAttribute("user",list);
%>

${list[0]}
${list[1]}