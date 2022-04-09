<%@ page import="com.example.OA_JSP.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %> <!DOCTYPE html>

<html>
	<head>
		<meta charset='utf-8'>
		<title>修改</title>
	</head>
	<body>
		<h1>修改</h1>
		<hr>
		<form action='${pageContext.request.contextPath}/dept/alert' method='post'>
			编号 <input type='text' name='Number' value="${deptlist.Number}" readonly> <br>
			名称 <input type="text" name="Name" value="${deptlist.Name}"> <br>
			地址 <input type="text" name="Location" value="${deptlist.Location}"> <br>
			
			<!-- 值得注意的是,这里的value值在进行修改的时候,应该是要显示的,所以这里的value的值应该是要动态获取的 -->
			<!-- 并且，部门编号是不能修改的，因为在 -->
			<input type='submit' name='修改'>
		</form>
		
	</body>
</html>
