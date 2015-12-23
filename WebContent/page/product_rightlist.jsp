<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />

<div class="tjStudio">
	<h2 class="tj-title">推荐素材</h2>
	<ul class="tjlist">

		<c:forEach var="m" varStatus="status" items="${list}">

			<li><span class="img-t"><a
					href="${pageContext.request.contextPath}/product/detail?pid=${m.id}"
					target="_blank"><img src="${m.thumb }" width="110" height="55" /></a>
			</span>
				<dl class="info">
					<dt>
						<a
							href="${pageContext.request.contextPath}/product/detail?pid=${m.id}"
							target="_blank">${m.name }</a>
					</dt>
					<dd class="p_b20">访问次数：${m.visitCount }次</dd>
				</dl></li>

		</c:forEach>


	</ul>
</div>