<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送消息</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<c:import url="/user/usernav" />
	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_userconfig.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<c:if test="${msg!=null}">
							<div class="alert alert-info">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								${msg}
							</div>

						</c:if>
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">发送消息</div>

							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/message/action/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收件人：</label>
											<div class="col-lg-10">
												<input name="sendto" type="text"
													class="form-control col-md-6" id="sendto" value="${sendTo }">
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">消息标题：</label>
											<div class="col-lg-10">
												<input name="title" type="text"
													class="form-control col-md-6" id="title">
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">消息内容：</label>
											<div class="col-lg-10">
												<textarea name="content" id="textarea-wysihtml5"
													class="form-control textarea-wysihtml5"
													placeholder="输入消息正文..."
													style="width: 100%; height: 200px"></textarea>
												<input type="hidden" name="_wysihtml5_mode" value="1">
											</div>
										</div>
										<button type="submit" class="btn btn-primary">发送</button>
										<button type="reset" class="btn btn-default">取消</button>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
		$(function() {
			// CKEditor Full
			$('textarea#ckeditor_full').ckeditor({
				height : '400px'
			});

			$('.textarea-wysihtml5')
					.wysihtml5(
							{
								stylesheets : [ '${pageContext.request.contextPath}/vendors/bootstrap-wysihtml5-rails-b3/vendor/assets/stylesheets/bootstrap-wysihtml5/wysiwyg-color.css' ]
							});

		})
	</script>
</body>
</html>

${js}