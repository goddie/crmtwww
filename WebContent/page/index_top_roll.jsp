<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />
<%-- <div id="slideshow" class="box_skitter fn-clear">
	<ul>
		<c:forEach var="m" varStatus="status" items="${list}">
			<li><a href="${m.redirectUrl }" target="_blank"><img
					src="${util.getCover(m.thumb,0) }" width="100%" height="100%" /></a></li>
		</c:forEach>

	</ul>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/web/js/slideshow.js"></script> --%>



<style type="text/css">
/* css 重置 */
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	background: #fff;
	font: normal 12px/22px 宋体;
}

img {
	border: 0;
}

a {
	text-decoration: none;
	color: #333;
}

/* 本例子css */
.slideBox {
 
	overflow: hidden;
	position: relative;
	text-align: center;
}

.slideBox .hd {
	height: 15px;
	overflow: hidden;
	position: absolute;
	right: 5px;
	bottom: 5px;
	z-index: 1;
}

.slideBox .hd ul {
	overflow: hidden;
	zoom: 1;
	float: left;
}

.slideBox .hd ul li {
	float: left;
	margin-right: 2px;
	width: 15px;
	height: 15px;
	line-height: 14px;
	text-align: center;
	background: #fff;
	cursor: pointer;
}

.slideBox .hd ul li.on {
	background: #f00;
	color: #fff;
}

.slideBox .bd {
	position: relative;
	height: 100%;
	z-index: 0;
}

.slideBox .bd li {
	zoom: 1;
	vertical-align: middle;
}

.slideBox .bd img {
 
	display: block;
	text-align: center;
	margin: 0 auto;
}


</style>

<div id="slideBox" class="slideBox">
	 
	<div class="bd">
		<ul>
		
		<c:forEach var="m" varStatus="status" items="${list}">
			<li><a href="${m.redirectUrl }" target="_blank"><img
					src="${util.getCover(m.thumb,0) }" /></a></li>
		</c:forEach>
			
		</ul>
	</div>

</div>

<script id="jsID" type="text/javascript">
	jQuery(".slideBox").slide({
		mainCell : ".bd ul",
		effect : 'fade',
		autoPlay : true,
		easing : 'swing',
		delayTime : 1000,
		mouseOverStop : true,
		pnLoop : true
	});
</script>
