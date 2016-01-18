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
												<input name="realname" type="text" value="${user.realname }"
													class="form-control col-md-6" id="realname">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">身份证</label>
											<div class="col-lg-10">
												<input name="licence" type="text" value="${user.licence }"
													class="form-control col-md-6" id="licence">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">身份证正反面</label>
											<div class="col-lg-10">
												<img id="upimage" src="${user.licenceImage }" alt="" width="240"
													height="180" /> <input name="thumb" id="thumb" value=""
													type="hidden" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传图片</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upload"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
												<p>
													证件要求：<br /> 1. 请上传有效的身份证图片，非证件图片不予受理。 <br /> 2.
													证件必须是彩色原件电子版，可以是扫描件或者数码拍摄照片。<br /> 3.
													仅支持.jpg,.bmp,.png,.gif的图片格式。图片大小不超过2M。
												</p>
											</div>

										</div>

										<button type="submit" class="btn btn-primary">提交</button>
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