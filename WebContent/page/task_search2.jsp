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
<title>潮人码头 - 文化产业电子商务平台 - 任务大厅</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<!--nav-->
	<c:import url="/webpage/webnav" />
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<div class="col-xs-12 col-sm-8 p_b10">
					<div class="c-search graybg3">
						<div class="c-s-select">
							任务<a href="#" class="iconXx"></a>
						</div>
						<input name="" type="text" placeholder="请输入关键词">
						<div class="c-s-search">
							<span class="iconSearch"></span>
						</div>
					</div>
					<p class="p_t10" style="display: none">
						热门任务：<a href="#">漫画</a>&nbsp;&nbsp;<a href="#">原画</a>&nbsp;&nbsp;<a
							href="#">Flash动画</a>&nbsp;&nbsp;<a href="#">游戏</a>&nbsp;&nbsp;<a
							href="#">网站建设</a>
					</p>
				</div>
				<div class="col-xs-12">
					<div class="t-fl">
						<h2 class="t-s-title" style="display: none">任务分类</h2>
						<dl class="s-dllist" style="display: none">
							<dt>商品性质</dt>
							<dd>
								<a href="#" class="current">全部</a><a href="#">平台作品</a><a
									href="#">平台服务</a>
							</dd>
						</dl>
						<h2 class="t-s-title m_t30">任务筛选</h2>
						<dl class="s-dllist">
							<dt>任务分类</dt>
							<dd>
								<a href="${pageContext.request.contextPath}/task/sub" class="current">全部</a>
								<c:forEach var="m" varStatus="status" items="${typeList}">
									<a
										href="${pageContext.request.contextPath}/task/sub?ptype=${m.id}">${m.name }</a>
								</c:forEach>

							</dd>
						</dl>
						<dl class="s-dllist" style="display: none">
							<dt>截止时间</dt>
							<dd>
								<a href="#" class="current">全部</a><a href="#">平台作品</a><a
									href="#">平台服务</a>
							</dd>
						</dl>
						<dl class="s-dllist" style="display: none">
							<dt>赏金额度</dt>
							<dd>
								<a href="#" class="current">全部</a><a href="#">100元以下</a><a
									href="#">100-500</a><a href="#">500-1000</a><a href="#">1000-2000</a><a
									href="#">2000-5000</a><a href="#">5000-2万</a><a href="#">2万以上</a>
							</dd>
						</dl>
						<dl class="s-dllist" style="display: none">
							<dt class="m_t30">排序方式</dt>
							<dd class="m_t30">
								<a href="#" class="current">默认排序</a><a href="#">截止时间</a><a
									href="#">投稿数</a><a href="#">价格</a>
							</dd>
						</dl>
					</div>
					<ul class="t-slist01">
						<li class="f_c_1490c6 fwb">
							<div class="w1">标题</div>
							<div class="w2">投标</div>
							<div class="w3">参与</div>
							<div class="w3">投稿</div>
						</li>

						<c:forEach var="m" varStatus="status" items="${list}">
							<li>
								<div class="w1">
									<span class="img-t"><img src="${m.thumb }"
										width="83" height="83"> </span>
									<dl class="info">
										<dt>
											<a href="${pageContext.request.contextPath}/task/detail?tid=${m.id }" class="f20 f_c_1490c6 fwb">${m.title }</a>
										</dt>
										<dd class="p_b20">${m.user.nickname }</dd>
									</dl>
								</div>
								<div class="w2">${m.bountyPrice }</div>
								<div class="w3">${m.visit }</div>
								<div class="w3">${m.submitCount }</div>
							</li>
						</c:forEach>
						 
					</ul>
					<div class="col-xs-12 p_b75">
						<div class="pages">
							${page } 
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3 sidebar-offcanvas" id="sidebar">
				<div class="m_l20">
					<div class="graybg2 p_b75">
						<h2 class="t-s-title1">推荐招标</h2>
						<ul class="t-slist02">


							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
						</ul>
						<h2 class="t-s-title1">推荐任务</h2>
						<ul class="t-slist02">
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
						</ul>
						<h2 class="t-s-title1">高额赏金</h2>
						<ul class="t-slist02">
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
							<li><span class="img-t"><img
									src="../images/touxiang4.jpg" width="83" height="83"> </span>
								<dl class="info">
									<dt class="f_c_fd0100">招标：暗标</dt>
									<dd class="p_b20">奥丁美术场景设计</dd>
								</dl></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--footer-->
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>


</body>
</html>