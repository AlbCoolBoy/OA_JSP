<%@ page import="com.example.OA_JSP.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ALB
  Date: 2022-04-09
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    User user=new User();
    List<User> userlist=new ArrayList<User>();

    User user1=new User(1,"justin","123456");
    User user2=new User(2,"bibber","23434122");
    User user3=new User(3,"Alan","734682");

    userlist.add(user1);
    userlist.add(user2);
    userlist.add(user3);

    request.setAttribute("userlist",userlist);

%>

<%--这里的item是要进行遍历的集合的名字，var后跟的是用于进行迭代的对象的名字，这个名字是自定义的，随便写--%>
<c:forEach items="${userlist}" var="s">
    age:${s.age} username:${s.username} password:${s.password}
    <br>
</c:forEach>