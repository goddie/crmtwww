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
								<div class="text-muted bootstrap-admin-box-title">企业认证</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/company/action/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">用户名</label>
											<div class="col-lg-10">

												<input disabled="disabled" name="realname" type="text" value="${user.username }"
													class="form-control col-md-6" id="realname">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">企业名</label>
											<div class="col-lg-10">
												<input name="name" type="text" value="${company.name }"
													class="form-control col-md-6" id="name">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">经营范围</label>
											<div class="col-lg-10">
												<select name="service" id="parentTypeId"
													class="form-control">

												</select> <span class="help-block"></span>
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">所属地区</label>
											<div class="col-lg-3">
												<select name="province" id="province"
													class="form-control">
												</select>
											</div>
											<div class="col-lg-3">
												<select name="city" id="city"
													class="form-control">

												</select>
											</div>
											<div class="col-lg-3">
												<select name="district" id="district"
													class="form-control">

												</select>
											</div>

										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">登记注册号码</label>
											<div class="col-lg-10">
												<input name="licence" type="text" value="${company.licence }"
													class="form-control col-md-6" id="licence">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">营业执照图片</label>
											<div class="col-lg-10">
												<img id="upimage" src="${company.licenceImage }" alt=""
													width="240" height="180" /> <input name="licenceImage" id="licenceImage"
													value="" type="hidden" />
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
													证件要求：<br /> 1. 请上传有效的营业执照图片，非证件图片不予受理。<br /> 2.
													证件必须是彩色原件电子版，可以是扫描件或者数码拍摄照片。<br /> 3.
													仅支持.jpg,.bmp,.png,.gif的图片格式。图片大小不超过2M。
												</p>
											</div>

										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">法人代表姓名</label>
											<div class="col-lg-10">
												<input name="person" type="text" value="${company.person }"
													class="form-control col-md-6" id="person">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">法人代表身份证号</label>
											<div class="col-lg-10">
												<input name="personLicence" type="text" value="${company.personLicence }"
													class="form-control col-md-6" id="personLicence">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">公司电话</label>
											<div class="col-lg-10">
												<input name="phone" type="text" value="${company.phone }"
													class="form-control col-md-6" id="phone">
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
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/js/PCASClass.js"></script>

<script type="text/javascript">
	$(function() {
		getParentType();
		new PCAS("province","city","district","","","");
	})

	function getParentType() {
		$.getJSON("${pageContext.request.contextPath}/tasktype/json/list", {
			parentId : ""
		}, function(data) {
			$.each(data, function(i, item) {
				selectAdd("#parentTypeId", item.name, item.name);
			});
		});
	}

	function selectAdd(name, value, text) {
		var selObj = $(name);
		selObj.append("<option value='"+value+"'>" + text + "</option>");
	}

	function upfile(jsonstr) {
		var obj = jQuery.parseJSON(jsonstr);

		var file = getThumb(obj.name, 200, 200);

		$('#upimage').attr('src', obj.path + file)

		$('#licenceImage').val(obj.path + file);
	}

	function getThumb(filename, w, h) {
		var ext = filename.substring(filename.lastIndexOf('.'));
		var newfile = filename.substring(0, filename.lastIndexOf('.')) + "_"
				+ w + "x" + h + ext;
		return newfile;
	}
</script>