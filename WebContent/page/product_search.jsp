<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>潮人码头 - 文化产业电子商务平台 - 创意商城</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="container">
		<div class="col-xs-12">
			<div class="col-sm-6">
				<div class="c-search graybg3 m_b16">
					<div class="c-s-select">
						商城<a href="#" class="iconXx"></a>
					</div>
					<div class="c-s-i">
						<form action="${pageContext.request.contextPath}/product/search"
							method="get" name="form1">
							<input name="key" type="text" placeholder="请输入关键词">
						</form>
					</div>
					<div class="c-s-search">
						<span onclick="form1.submit();" class="iconSearch"></span>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<a href="${pageContext.request.contextPath}/product/v/add"
					class="pubBtn" target="_blank">发布商品</a>
			</div>
		</div>
	</div>

	<div class="container">
		<c:forEach var="m" varStatus="status" items="${list}">
			<div class="col-xs-12 col-sm-3">
				<div class="picinfo m_lr15_b25">
					<a
						href="${pageContext.request.contextPath}/product/detail?pid=${m.id}">
<c:if test="${not empty m.thumb }"><img
											src="${m.thumb }" width="232" height="161"></c:if>
											<c:if test="${empty m.thumb}"><img
											src="${pageContext.request.contextPath}/resource/web/images/nopic_232.png" width="232" height="161"></c:if>
</a>
					<p class="p_tb15">
						<a
							href="${pageContext.request.contextPath}/product/detail?pid=${m.id}">${m.name }</a>
					</p>
					<a
						href="${pageContext.request.contextPath}/product/detail?pid=${m.id}"
						class="whiteBtn">￥${m.price }</a>
					<dl class="s-dllist1">
						<dt>
							<a href="#"><img src="${m.user.head }"
								style="box-sizing: content-box; -moz-box-sizing: content-box; -ms-box-sizing: content-box; width: 40px; height: 40px; border-radius: 40px;"></a>
						</dt>
						<dd class="f16 p_t5">${m.user.nickname }</dd>
						<dd class="f13 f_c_a1a1a1">最近${m.tradeCount }人成交</dd>
					</dl>
				</div>
			</div>
		</c:forEach>

		<div class="col-xs-12 p_b75">
			<div class="pages">${page }</div>
		</div>

	</div>

	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>