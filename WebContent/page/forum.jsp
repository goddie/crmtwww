<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>潮人码头 - 文化产业电子商务平台 - 创意社区</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />


	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-6 col-sm-3">
				<c:import url="/forumtype/menu" />
			</div>
			<div class="col-xs-12 col-sm-9">
				<div class="m_l20">
					<div class="c-search bluebg m_b19">
						<div class="c-s-select">
							帖子<a href="#" class="iconXx"></a>
						</div>
						<input name="" type="text" placeholder="请输入关键词">
						<div class="c-s-search">
							<span class="iconSearch"></span>
						</div>
					</div>
					<c:import url="/forum/forumroll" />

				</div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->
	</div>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="m_t50">

				<c:import url="/forum/forumtab" />

				<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
					<div id="jpzt" style="display:none">
						<h2 class="c-title">精品专题</h2>
						<a href="#"><img
							src="${pageContext.request.contextPath}/resource/web/images/pic16.jpg"
							width="300" height="90"></a>
						<ul class="c-list02">
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
							<li><a href="#">石泰峰、周乃翔调研蓝海创意云</a></li>
						</ul>
					</div>
					<div id="rywt">
						<h2 class="c-title">热议问题</h2>
						<ul class="c-list02">
						<c:forEach var="m" varStatus="status" items="${list2}">
								<li><a href="${pageContext.request.contextPath}/forum/detail?id=${m.id}">${m.title }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript">
	function showTab_b(num) {
		for (i = 0; i < 5; i++) {
			document.getElementById("tab" + i).style.display = "none";
			document.getElementById("id" + i).className = "noborder";

		}
		document.getElementById("tab" + num).style.display = "block";
		document.getElementById("id" + num).className = "orangeborder";
	}
</script>