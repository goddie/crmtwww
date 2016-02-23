<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>企业资料</title>
</head>
<body class="bootstrap-admin-with-small-navbar">

	<c:import url="/user/usernav" />


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
								<div class="text-muted bootstrap-admin-box-title">企业认证</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="" name="form1" method="post"
									class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">用户名</label>
											<div class="col-lg-10">
												<p>${user.username }</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">企业名</label>
											<div class="col-lg-10">
												<p>${company.name }</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">经营范围</label>
											<div class="col-lg-10">
												<p>${company.service }</p>
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">所属地区</label>
											<div class="col-lg-10">


												<p>${company.province } ${company.city } </p>
											</div>
										 

										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">登记注册号码</label>
											<div class="col-lg-10">

												<p>${company.licence }</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">营业执照图片</label>
											<div class="col-lg-10">
												<img id="upimage" src="${company.licenceImage }" alt=""
													width="240" height="180" /> 

											</div>
										</div>



										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">法人代表姓名</label>
											<div class="col-lg-10">
												<p>${company.person }</p>


											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">法人代表身份证号</label>
											<div class="col-lg-10">

												<p>${company.personLicence }</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">公司电话</label>
											<div class="col-lg-10">

												<p>${company.phone }</p>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/PCASClass.js"></script>

<script type="text/javascript">
	$(function() {
		getParentType();
		new PCAS("province", "city", "district", "", "", "");
	})

	function getParentType() {
		$.getJSON("${pageContext.request.contextPath}/tasktype/json/list", {
			parentId : ""
		}, function(data) {
			$.each(data, function(i, item) {
				selectAdd("#parentTypeId", item.id, item.name);
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

		$('#thumb').val(obj.path + file);
	}

	function getThumb(filename, w, h) {
		var ext = filename.substring(filename.lastIndexOf('.'));
		var newfile = filename.substring(0, filename.lastIndexOf('.')) + "_"
				+ w + "x" + h + ext;
		return newfile;
	}
</script>