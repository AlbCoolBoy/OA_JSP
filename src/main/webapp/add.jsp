<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
		<h1>新增</h1>
		<hr>
		<form action="${pageContext.request.contextPath}/dept/add" method="post" charset="utf-8">
			编号 <input type="text" name="Number"> <br>
			部门 <input type="text" name="Name"> <br>
			地址 <input type="text" name="Location"> <br>
			<input type="submit" name="保存">
		</form>
	</body>
</html>
