<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/album/uploadImage2"
		method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td
					style="text-align: center; vertical-align: middle; font-size: 9pt;"><input
					type="file" name="file" /><input
					type="hidden" name="fieldName" value="${fieldName }" /> <input type="submit" value="点击上传" />
					&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>

	</form>
</body>
</html>
${js}
${msg}