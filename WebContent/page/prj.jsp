<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

加载中...
<div style="display: none">
	<form id="form1" method="post" action="http://prj.mediamarina.com:8088/index.php?s=/Admin/Index/login">
		<input name="username" value="${user.username }" />
		<input name="password" value="${user.password }" />
	</form>
</div>

</body>
</html>

<script type="text/javascript">

form1.submit();

</script>