<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="m" varStatus="status" items="${list}">
	<li><span class="t-price">${m.bountyPrice }</span><span
		class="t-info"><a
			href="${pageContext.request.contextPath}/task/detail?tid=${m.id}">${m.title }</a><b
			class="iconHot"></b></span><span class="t-number"><img
			src="${pageContext.request.contextPath}/resource/web/images/icon_touxiang.png"
			width="14" height="14">${m.submitCount }</span></li>
</c:forEach>