<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>潮人码头 - 文化产业电子商务平台</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".slideGroup .slideBox").slide({
			mainCell : "ul",
			vis : 3,
			autoPlay : true,
			prevCell : ".new-c-prev",
			nextCell : ".new-c-next",
			effect : "leftLoop"
		});
	});
</script>
</head>
<body>

	<c:import url="/webpage/webnav" />

	<div id="wrapper">
		<div id="yc-mod-slider">
			<c:import url="/webpage/indextoproll" />
		</div>


	</div>


	<c:import url="/webpage/indexnews" />


	<div class="">

		<c:import url="/task/indextask" />

		<div class="c-create">
			<img
				src="${pageContext.request.contextPath}/resource/web/images/zxcz.jpg"
				width="100%" height="600" />
			<div class="c-c-info">
				<p class="zxcz">在线创作</p>
				<a href="${pageContext.request.contextPath}/webpage/online"
					class="redBtn" target="_blank">开始体验</a>
			</div>
		</div>
		<div class="c-case clearfix">
			<!--成功案例-->
			<div class="new-c-case slideGroup">
				<div class="new-c-casetitle">
					<h2 class="c-t-title p_t50b20 tac">成功案例</h2>
					<p class="p1 tac">潮人码头与行业众多知名企业保持长期合作关系，积累了丰富的渲染经验，具备其他平台不可比拟的优势。</p>
				</div>
				<div class="new-c-castlist slideBox">
					<a class="new-c-prev" href="javascript:void(0)"></a>
					<ul>${caselist }
					</ul>
					<a class="new-c-next" href="javascript:void(0)"></a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>

</body>
</html>

