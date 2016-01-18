<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>潮人码头 - 文化产业电子商务平台 - 任务大厅</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-2" id="seriveClassify">
				<h2 class="t-title1">服务分类</h2>
				<div class="redbg">
					<ul class="t-list01">
						<c:import url="/tasktype/menu" />
						 
					</ul>
				</div>
			</div>
			<div class="col-xs-12 col-sm-10">
				<div class="m_l20">
					<div class="col-xs-12 col-sm-10 m_b19">
						<div class="c-search graybg3" style=" width:100%;">

							<div class="c-s-select">搜索任务</div>
							<form action="${pageContext.request.contextPath}/task/search" method="get" name="form1">
								<input name="key" type="text" placeholder="请输入关键词">
							</form>
							<div class="c-s-search">
								<span onclick="form1.submit();" class="iconSearch"></span>
							</div>
						</div>

					</div>
					<div class="col-xs-12 col-sm-2">
						<a href="${pageContext.request.contextPath}/task/v/add?type=1"
							class="grayBtn2 m_l20" target="_blank">发任务</a><a style="display:none"
							href="${pageContext.request.contextPath}/task/v/add?type=3" 
							class="grayBtn2 m_l10" target="_blank">项目招标</a><a  style="display:none"target="_blank"
							href="${pageContext.request.contextPath}/task/v/add?type=5"
							class="grayBtn2 m_l10">直接雇佣</a>
					</div>
					<c:import url="/task/taskroll" />
				</div>
			</div>
		</div>
	</div>
	<div class="graybg2">
		<div class="container m_t30">
			<div class="row row-offcanvas row-offcanvas-right m_b19 p_t25">
				<h2 class="t-title2">推荐任务</h2>
				<div class="col-xs-12">
					<div class="col-xs-12 col-sm-4">
						<div class="tjrwBorder m_r35b20">
							<h3 class="t-title3">网站建设</h3>
							<ul class="t-list02">

								<c:import
									url="/task/block?type=21f919ff-1fa5-4766-b50d-0897cc4283a1&count=5" />


							</ul>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4">
						<div class="tjrwBorder m_r35b20">
							<h3 class="t-title3">工业设计</h3>
							<ul class="t-list02">
								<c:import
									url="/task/block?type=0a64a7dc-d2e6-4494-908d-4aa78830f027&count=5" />
							</ul>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4">
						<div class="tjrwBorder">
							<h3 class="t-title3">创意祝福</h3>
							<ul class="t-list02">
								<c:import
									url="/task/block?type=2e8a4b9d-41c0-434c-b9f6-e83cd40f017e&count=5" />
							</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-12">
					<div class="col-xs-12 col-sm-4">
						<div class="tjrwBorder m_r35b20">
							<h3 class="t-title3">服饰设计</h3>
							<ul class="t-list02">
								<c:import
									url="/task/block?type=424faa48-947b-4c58-ab59-e4ab20ef40f3&count=5" />
							</ul>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4">
						<div class="tjrwBorder m_r35b20">
							<h3 class="t-title3">建筑设计</h3>
							<ul class="t-list02">
								<c:import
									url="/task/block?type=4bbf5c29-f016-43ee-b9c4-dde9dba8017a&count=5" />
							</ul>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4">
						<div class="tjrwBorder">
							<h3 class="t-title3">移动应用</h3>
							<ul class="t-list02">
								<c:import
									url="/task/block?type=79227339-5764-46c2-bad6-6733552d91af&count=5" />
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container p_t25">
		<div class="row row-offcanvas row-offcanvas-right">
			<h2 class="t-title2">个人服务商</h2>
			<c:import url="/user/personblock?count=4" />
		</div>
	</div>
	<div class="container p_b40">
		<div class="row row-offcanvas row-offcanvas-right">
			<h2 class="t-title2">企业服务商</h2>
			<c:import url="/company/companyblock?count=4" />
		</div>
	</div>

	<!--footer-->
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>

</body>
</html>