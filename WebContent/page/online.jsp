<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>潮人码头 - 文化产业电子商务平台 - 在线办公</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="o-office">
		<img
			src="${pageContext.request.contextPath}/resource/web/images/o-banerbg.jpg"
			width="100%" />
		<div class="o-o-info">
			<p class="o-o-txt">项目协同创作工具</p>
			<a class="blueBtn2"  href="https://cloud3.yxhltech.com" target="_self">立即体验</a>
		</div>
	</div>
	<div class="m_t30b50">
		<div class="container">
			<div class="row row-offcanvas row-offcanvas-right">
				<div class="col-xs-12 col-sm-9">
					<div class="grayborder1 clearfix">
						<h2 class="o-title">热门工具<span style=" font-size: 14px; color:#f00;">(使用工具需要开通权限，如需使用，请联系潮人码头项目管理员给予开通。)</span></h2>
						<div class="col-xs-12 grayborder2">
							<c:import
								url="/webpage/onlineblock?tid=9c443fd0-2263-4dfe-b9c1-ed44821c9e0e" />



						</div>
						<h2 class="o-title">热门素材</h2>
						<c:import
							url="/product/hotproduct" />

					</div>
				</div>
				<div class="col-xs-12 col-sm-3 sidebar-offcanvas" id="sidebar">
					<div class="m_l20 graybg2">
						<div class="p_20">
							 
							 
							 
							<c:import
							url="/product/rightlist" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>