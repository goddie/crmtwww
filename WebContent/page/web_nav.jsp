<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default navbar-static-top"
	style="">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><img
				src="${pageContext.request.contextPath}/resource/web/images/logo.png" /></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/webpage/index"
					target="_self">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/task/index"
					target="_self">任务大厅</a></li>
				<li><a
					href="${pageContext.request.contextPath}/webpage/online"
					target="_self">在线创作</a></li>
				<li><a
					href="http://oa.yxhltech.com"
					target="_self">在线办公</a></li>
				<li><a href="${pageContext.request.contextPath}/product/index"
					target="_self">创意商城</a></li>
				<li><a href="${pageContext.request.contextPath}/forum/index"
					target="_self">创意社区</a></li>

				<c:if test="${user==null}">
					<li><a
						href="${pageContext.request.contextPath}/webpage/login"
						target="_self" class="login">登陆</a></li>
					<li><a
						href="${pageContext.request.contextPath}/webpage/reg"
						target="_self" class="login">注册</a></li>
				</c:if>

				<c:if test="${user!=null}">
					<li><a
						href="${pageContext.request.contextPath}/user/v/index"
						target="_self" class="login home">用户中心</a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/action/logout"
						target="_self" class="login home" title="${user.username }">退出</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

<style type="text/css">
.navbar-nav>li>a.login {
	width: 80px;
	font-szie: 12px;
}
</style>