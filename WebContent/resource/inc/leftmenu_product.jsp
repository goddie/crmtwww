<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>商品管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/product/v/add"><i
					class="glyphicon glyphicon-chevron-right"></i>发布商品</a></li>
			<li><a href="${pageContext.request.contextPath}/product/v/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>我发布的</a></li>
					<li><a href="${pageContext.request.contextPath}/product/v/notpay?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>待支付的</a></li>
			<li><a href="${pageContext.request.contextPath}/product/v/buylist?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>已购买的</a></li>
			<li><a href="${pageContext.request.contextPath}/product/v/selllist?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>我销售的</a></li>
			<li><a href="${pageContext.request.contextPath}/collect/v/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>我收藏的</a></li>

		</ul></li>
</ul>