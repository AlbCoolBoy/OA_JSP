<%@page contentType="text/html;charset=UTF-8" %> <!DOCTYPE html>
<%--下面的设置是设定在访问某些页面的时候不生成session对象，但是这个不能随便写，因为这样的话，内置对象就不能用了--%>
<%--
<%@page session="false"%>
--%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>error</title>
</head>
<body>
<h1>操作失败
</h1>
<br>
<a href="javascript:void(0)" onclick="window.history.back() " charset="utf-8">返回</a>
</body>
</html>

