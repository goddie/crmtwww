<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>账户设置</title>
</head>
<body class="bootstrap-admin-with-small-navbar">

	<c:import url="/member/adminnav" />


	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/admin_left.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">实名认证</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/user/action/checkperson"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">真实姓名</label>

											<div class="col-lg-10">
												<p class="form-control-static">${user.realname }</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">身份证</label>
											<div class="col-lg-10">
												<p class="form-control-static">${user.licence }</p>
											</div>


										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">身份证正反面</label>

											<div class="col-lg-10">
												<p class="form-control-static">
													<img id="upimage" src="${user.licenceImage }" alt=""
														width="240" height="180" />
												</p>
											</div>



										</div>


										<a href="${ref} "  class="btn btn-primary">返回</a>
										 

									</fieldset>


								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
<script type="text/javascript">
	function upfile(jsonstr) {
		var obj = jQuery.parseJSON(jsonstr);

		var file = getThumb(obj.name, 200, 200);

		$('#upimage').attr('src', obj.path + file)

		$('#thumb').val(obj.path + file);
	}

	function getThumb(filename, w, h) {
		var ext = filename.substring(filename.lastIndexOf('.'));
		var newfile = filename.substring(0, filename.lastIndexOf('.')) + "_"
				+ w + "x" + h + ext;
		return newfile;
	}
</script>