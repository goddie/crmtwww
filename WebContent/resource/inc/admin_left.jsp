<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">

	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>文章管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/article/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新建文章</a></li>
			<li><a
				href="${pageContext.request.contextPath}/article/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>文章列表</a></li>
			<li><a
				href="${pageContext.request.contextPath}/articletype/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新建分类</a></li>
			<li><a
				href="${pageContext.request.contextPath}/articletype/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>分类列表</a></li>
		</ul></li>

	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>用户管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/user/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>注册用户</a></li>
			<li><a
				href="${pageContext.request.contextPath}/user/admin/list?type=1&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>个人用户</a></li>
			<li><a
				href="${pageContext.request.contextPath}/user/admin/list?type=2&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>企业用户</a></li>
			<li><a
				href="${pageContext.request.contextPath}/user/admin/review?type=1&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>个人认证审核</a></li>

			<li><a
				href="${pageContext.request.contextPath}/user/admin/review?type=2&p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>企业认证审核</a></li>
		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>创意商城-商品管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/product/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>商品列表</a></li>



		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>任务大厅-任务管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/task/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>任务列表</a></li>
			<li><a
				href="${pageContext.request.contextPath}/tasktype/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新建任务分类</a></li>
			<li><a
				href="${pageContext.request.contextPath}/tasktype/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>任务分类列表</a></li>


		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>创意社区-社区管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/forum/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>帖子列表</a></li>
			<li><a
				href="${pageContext.request.contextPath}/forumtype/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新建社区分类</a></li>
			<li><a
				href="${pageContext.request.contextPath}/forumtype/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>社区分类列表</a></li>


		</ul></li>
</ul>