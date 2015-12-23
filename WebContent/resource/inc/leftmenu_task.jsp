<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>发布的任务</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/task/v/list?type=1&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>单人悬赏</a></li>
			<li><a
				href="${pageContext.request.contextPath}/task/v/list?type=2&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>计件悬赏</a></li>
			<li style="display:none"><a
				href="${pageContext.request.contextPath}/task/v/list?type=3&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>项目招标</a></li>
			<li style="display:none"><a
				href="${pageContext.request.contextPath}/task/v/list?type=4&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>项目交付</a></li>
			<li style="display:none"><a
				href="${pageContext.request.contextPath}/task/v/list?type=5&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>直接雇佣</a></li>
		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>参与的任务</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/submit/v/list?type=1&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>单人悬赏</a></li>
			<li><a
				href="${pageContext.request.contextPath}/submit/v/list?type=2&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>计件悬赏</a></li>
			<li style="display:none"><a
				href="${pageContext.request.contextPath}/submit/v/list?type=3&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>项目招标</a></li>
			<li style="display:none"><a
				href="${pageContext.request.contextPath}/submit/v/list?type=4&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>项目交付</a></li>
			<li style="display:none"><a
				href="${pageContext.request.contextPath}/submit/v/list?type=5&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>直接雇佣</a></li>

		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>信用评价</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>信誉成长</a></li>
		</ul></li>
	<li style="display: none;"><a href="#"><i
			class="glyphicon glyphicon-chevron-down"></i>维权管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>维权管理</a></li>
		</ul></li>
	<li style="display: none;"><a href="#"><i
			class="glyphicon glyphicon-chevron-down"></i>关注的人才</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>关注的人才</a></li>
		</ul></li>
</ul>