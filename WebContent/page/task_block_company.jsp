<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="m" varStatus="status" items="${list}">
	<div class="col-xs-12 col-sm-3">
		<div class="grayborder m_lr15_b25">
			<a
				href="${pageContext.request.contextPath}/user/usersite?uuid=${m.user.id}"
				target="_blank"><img src="${m.user.head }" width="100%"
				height="190"></a>
			<div class="m_lr15_b25 tac">
				<p class="p_b5 f20">
					<a class="f_c_0a538e" href="${pageContext.request.contextPath}/user/usersite?uuid=${m.user.id}" target="_blank">${m.user.nickname }</a>
				</p>
				<p class="p_b10">${m.company.name }</p>
				<p>${m.user.introduce }</p>
			</div>
		</div>
	</div>
</c:forEach>