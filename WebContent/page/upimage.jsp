<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/album/uploadImage"
		method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td
					style="text-align: center; vertical-align: middle; font-size: 9pt;"><input
					type="file" name="file" /> <input type="submit" value="上传文件" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${ischeck}">
					<input name="cover" checked="checked"
					type="checkbox" value="1" />只更新封面图，不写入内容中
					</c:if>
					</td>
			</tr>
		</table>

	</form>
</body>
</html>
${js}
${msg}