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
	<c:import url="/webpage/webnav" />
	<div class="graybg">
		<div class="container">
			<div class="col-xs-12 col-sm-9">
				<p>首页 > 任务大厅 > 任务列表 > 任务详情</p>
				<h1 class="d-title">${entity.title }</h1>
				<p class="p_t10">
					<a href="${pageContext.request.contextPath}/user/usersite?uuid=${entity.user.id}" target="_blank"><img src="${entity.user.head }" width="27" height="27">&nbsp;&nbsp;${entity.user.nickname }</a>
				</p>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="clearfix">
					<span class="fl">
						<p class="f24 fwb">${entity.visit }</p>
						<p class="p_l6">浏览人数</p>
					</span> <span class="fr p_r30">
						<p class="f24 fwb">${entity.submitCount }</p>
						<p class="p_l6">交稿数量</p>
					</span>
				</div>
				<p class="p_b10">
					<c:if test="${canSubmit==1 && hasSubmit==0}">
					<a
						href="javascript:void(0)" onclick="addSubmit()"
						class="redBtn3">承接任务</a>
					</c:if>
					<c:if test="${hasSubmit==1}">
					<a
						href="${pageContext.request.contextPath}/submit/v/useradd?taskId=${entity.id }"
						class="redBtn3">任务交稿</a>
					</c:if>
				</p>
				<p>
					<a style="display: none;" href="javascript:void(0)"
						title="${entity.QQ }" class="grayBtn1">QQ联系</a><a
						href="${pageContext.request.contextPath}/message/v/add?sendTo=${entity.user.username }"
						target="_blank" class="grayBtn1">发私信</a><a href="#"
						class="grayBtn1" style="display: none;">举报</a>
				</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<div class="graybg1">
					<ul class="progressBar">
					<c:if test="${entity.status==0 }">
					<li class="bar1"></li>
					</c:if>
					<c:if test="${entity.status==1 }">
					<li class="bar2"></li>
					</c:if>
					<c:if test="${entity.status==2 }">
					<li class="bar3"></li>
					</c:if>
					<c:if test="${entity.status==3 }">
					<li class="bar4"></li>
					</c:if>
					<c:if test="${entity.status==4 }">
					<li class="bar5"></li>
					</c:if>
						
						<!--<li class="bar2"></li>
            <li class="bar3"></li>
            <li class="bar4"></li>
            <li class="bar5"></li>
            <li class="bar6"></li>
            <li class="bar7"></li>-->
						<li class="pbTxt"> 
							<p>发布</p>
							<p>审核</p>
							<p>投稿</p>
							<p>选稿</p>
							<p>结束</p></li>
					</ul>
				</div>
				<p class="p01">${entity.content }</p>
				<div class="clearfix">
					<h2>任务留言</h2>
					<textarea id="content" name="content" cols="" rows="" class="txt"
						placeholder="我要参与此任务的竞标承接(说出你的优势，告诉买家为何选择你)"></textarea>
					<a href="javascript:void(0)" onclick="addFeedback()"
						class="redBtn4 fr">发表评论</a>
				</div>
				<div class="p_t25">
					<h2>任务留言</h2>
					<ul class="detaillist">
						<c:import url="/feedback/tasklist">
							<c:param name="taskId" value="${entity.id }" />
							<c:param name="p" value="1" />
						</c:import>

					</ul>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>
<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

<script type="text/javascript">
	function addFeedback() {
		$.getJSON("${pageContext.request.contextPath}/feedback/json/add", {
			parentId : "",
			taskId : "${entity.id}",
			content : $("#content").val(),
			t : new Date()
		}, function(obj) {

			if (obj.code != 1) {
				alert("提交成功");
			} else {
				window.location.reload();
			}

		});
	}
	
	function addSubmit() {
		$.getJSON("${pageContext.request.contextPath}/submit/json/add", {
			taskId : "${entity.id}",
			t : new Date()
		}, function(obj) {

			if (obj.code != 1) {
				alert(obj.msg);
			} else {
				alert("承接任务成功，请按时交稿。");
				window.location.reload();
			}

		});
	}
</script>
${js }
