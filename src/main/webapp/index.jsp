<%@page contentType="text/html;charset=UTF-8" %> <!DOCTYPE html>


<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
		<h1>Login</h1>
		<hr>
		<%--<a href="/OA/dept/list">查看部门列表</a>--%>
		<form action="<%=request.getContextPath()%>/user/login" method="post">
			Account<input type="text" name="account">
			<br>
			Password<input type="password" name="password">
			<br>
			<input type="checkbox" name="f" value="y">十天内免登录 <br><br>
			<input type="submit" value="login">

			<br>
		</form>
	</body>
</html>
