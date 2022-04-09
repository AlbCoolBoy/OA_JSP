<%@ page import="com.example.OA_JSP.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>详情</title>

		<script type='text/javascript'>
			function del(dno){
				if(window.confirm('Are you sure')){
					document.location.href=='/oa/delete?number='+dno
				}
			}
		</script>
	</head>
	<body>
		<body>
			<h1>部门详情</h1>
			<hr> 
			部门编号：${deptlist.Number}<br>		<%--这里应该是刚才转发收绑定的请求名--%>
			部门：${deptlist.Name}<br>
			部门位置：${deptlist.Location}<br>
			<input type='button' value='后退' onclick='window.history.back()'>
			
		</body>
		
	</body>
</html>
