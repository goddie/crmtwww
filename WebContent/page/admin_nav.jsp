<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- small navbar -->
<nav
	class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm"
	role="navigation"></nav>
<nav
	class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
	role="navigation">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".main-navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/user/v/index">个人中心(${member.username})</a>
				</div>
				<div class="collapse navbar-collapse main-navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a
							href="${pageContext.request.contextPath}/webpage/index">潮人码头</a></li>
						<li><a
						href="${pageContext.request.contextPath}/member/action/logout"
						target="_self" class="login home">退出 ${member.username }</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
		</div>
	</div>
	<!-- /.container -->
</nav>