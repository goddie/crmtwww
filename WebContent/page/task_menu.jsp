<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="m" varStatus="status" items="${list}">
	<li><a href="${pageContext.request.contextPath}/task/sub?ptype=${m.parent.id}">${m.parent.name }</a>
			<ul>
			<c:forEach var="s" varStatus="status" items="${m.sub}">
				<li><a href="${pageContext.request.contextPath}/task/sub?stype=${s.id}">${s.name }</a></li>
			</c:forEach>
		</ul></li>

</c:forEach>