<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />
<div id="slideshow" class="box_skitter fn-clear">
	<ul>
		<c:forEach var="m" varStatus="status" items="${list}">
			<li><a href="${m.redirectUrl }" target="_blank"><img
					src="${util.getCover(m.thumb,0) }" width="100%" height="100%" /></a></li>
		</c:forEach>

	</ul>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/web/js/slideshow.js"></script>