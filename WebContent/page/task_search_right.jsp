<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="graybg2 p_b75">
	<h2 class="t-s-title1">最新任务</h2>
	<ul class="t-slist02">
		<c:forEach var="m" varStatus="loop" items="${list1}">

			<li><span class="img-t"><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" target="_blank"><img
					src="${m.thumb }" width="83" height="83"></a> </span>
				<dl class="info">
					<dt class="f_c_fd0100">赏金：${m.bounty }</dt>
					<dd class="p_b20"><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" target="_blank">${m.title }</a></dd>
				</dl></li>
			
		</c:forEach>
	</ul>
	<h2 class="t-s-title1">热门任务</h2>
	<ul class="t-slist02">
		<c:forEach var="m" varStatus="loop" items="${list2}">

			<li><span class="img-t"><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" target="_blank"><img
					src="${m.thumb }" width="83" height="83"></a> </span>
				<dl class="info">
					<dt class="f_c_fd0100">赏金：${m.bounty }</dt>
					<dd class="p_b20"><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" target="_blank">${m.title }</a></dd>
				</dl></li>
			
		</c:forEach>
	</ul>
	<h2 class="t-s-title1">高额赏金</h2>
	<ul class="t-slist02">
		<c:forEach var="m" varStatus="loop" items="${list3}">

			<li><span class="img-t"><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" target="_blank"><img
					src="${m.thumb }" width="83" height="83"></a> </span>
				<dl class="info">
					<dt class="f_c_fd0100">赏金：${m.bounty }</dt>
					<dd class="p_b20"><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" target="_blank">${m.title }</a></dd>
				</dl></li>
			
		</c:forEach>
	</ul>
</div>