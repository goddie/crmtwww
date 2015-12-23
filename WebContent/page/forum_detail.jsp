<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>潮人码头 - 文化产业电子商务平台 - 社区</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 ">
				<div class="forum-title h3">${entity.title }</div>
				<div class="forum-block clearfix">

					<div class="forum-user col-md-2">
						<a href="${pageContext.request.contextPath}/user/usersite?uuid=${entity.user.id }"><img class="thumb" src="${entity.user.head }"
							width="80" height="80" />${entity.user.nickname }</a>
					</div>
					<div class="forum-content col-md-10 ">
						<div class="content-body">${entity.content }</div>
						<div class="content-date col-md-6 col-md-offset-6 text-right">
							发布时间:${entity.createdDate }</div>
					</div>
				</div>

				<c:forEach var="m" varStatus="status" items="${list}">

					<div class="forum-block clearfix">

						<div class="forum-user col-md-2">
							<a href="${pageContext.request.contextPath}/user/usersite?uuid=${m.user.id }"><img class="thumb" src="${m.user.head }"
								width="80" height="80" />${m.user.nickname }</a>
						</div>
						<div class="forum-content col-md-10 ">
							<div class="content-body">${m.content }</div>
							<div class="content-date col-md-6 col-md-offset-6 text-right">
								发布时间:${m.createdDate }</div>
						</div>
					</div>

				</c:forEach>


			</div>
			<div class="row text-center">${pageHtml }</div>

			<div class="col-md-12 ">
				<div class="forum-new">
					<a href="${pageContext.request.contextPath}/forum/user/add?id=${entity.subType.id}" class="btn  btn-danger">发表新帖</a>
					 
				</div>
				<div class="forum-block clearfix">

					<div class="forum-user col-md-2"></div>
					<div class="forum-content col-md-10" style="padding-left: 35px;">
						<div class="content-body">
							<form
								action="${pageContext.request.contextPath}/forumreply/action/add"
								name="form1" method="post" class="form-horizontal"
								onsubmit="return checkLogin()">
								<input type="hidden" name="forumId" value="${entity.id }" />



								<div class="form-group col-md-11">

									<textarea id="ckeditor_standard" name="content"
										class="form-control textarea-wysihtml5" placeholder="输入正文..."></textarea>

								</div>
								<div class="form-group col-md-10 ">
									<button type="submit" class="btn  btn-danger">回复</button>

								</div>

							</form>
						</div>

					</div>
				</div>



			</div>
			<div class="col-md-12 ">&nbsp;</div>
		</div>
	</div>
	<!--footer-->
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>
${msg }
<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript">
	var editor;

	$(function() {

		// CKEditor Standard
		$('textarea#ckeditor_standard').ckeditor(
				{
					height : '240px',
					toolbar : [

							[ 'Cut', 'Copy', 'Paste', 'PasteText',
									'PasteFromWord', '-', 'Undo', 'Redo' ], // Defines toolbar group without name.
							{
								name : 'basicstyles',
								items : [ 'Bold', 'Italic' ]
							} ]
				});

	})

	function checkLogin() {

		if($('#ckeditor_standard').val()==null)
		{
			return false;
		}
		
		var msg=false;
		
		$.ajax({
			type : "GET",
			async : false,
			cache : false,
			url : "${pageContext.request.contextPath}/webpage/json/islogin",
			data : "t=new Date()",
			success : function(rs) {

				if (rs.code == 1) {
					msg = true;
				} else {
					alert('请先登录再发帖');
					msg = false;
				}

			}
		});

		return msg;
	}
</script>