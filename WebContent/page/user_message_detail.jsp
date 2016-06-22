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
				<c:import url="/user/userleft" />
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
								<div class="text-muted bootstrap-admin-box-title">查看消息</div>

							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								 
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">发件人：</label>
											<div class="col-lg-10">
												<p>${entity.from.username }(${entity.from.nickname })</p>
											 
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收件人：</label>
											<div class="col-lg-10">
												<p>${entity.sendTo.username }(${entity.sendTo.nickname })</p>
											 
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">消息标题：</label>
											<div class="col-lg-10">
												<p>${entity.title }</p>
												 
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">消息内容：</label>
											<div class="col-lg-10">
												<p>${entity.content }</p>
											</div>
										</div>

									</fieldset>
					 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>
</html>

${js}