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
<title>潮人码头 - 文化产业电子商务平台 - 新闻中心</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />

	<div class="container">
		<h3>潮人码头 > 新闻中心</h3>
		
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="">
				<div class="col-md-9 p_b20">
					<div class="m_r20">

						<div class="tab_content" id="tab0">
							<c:forEach var="m" varStatus="status" items="${list}">
								<ul class="detaillist">
									<li class="col-md-12"><span class="img-t col-md-2"><img
											src="${webutil.getThumb(m.thumb,2)}" width="109" height="68"> </span>
										<dl class="info col-md-9">
											<dt>
												<span class="f_c_0a538e f20"><a target="_blank"
													href="${pageContext.request.contextPath}/article/detail?id=${m.id}">${m.title }</a></span>
											</dt>
											<dd>
												<span class="f_c_6cbef5"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.createdDate }
											</dd>


										</dl></li>

								</ul>
							</c:forEach>

							<div class="row text-center">${pageHtml }</div>
						</div>

					</div>
				</div>
				<div class="col-md-3 sidebar-offcanvas" id="sidebar">
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