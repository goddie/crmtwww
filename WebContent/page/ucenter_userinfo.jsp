<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>账户资料</title>
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
								<div class="text-muted bootstrap-admin-box-title">个人资料</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/user/action/update"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">昵称</label>
											<div class="col-lg-10">

												<input name="nickname" type="text" value="${user.nickname }"
													class="form-control col-md-6" id="nickname">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">性别</label>
											<div class="col-lg-10">
												<label class="uniform"> <input class="sex"
													type="radio" name="sex" value="男" checked="checked" />男
												</label> <label class="uniform"> <input class="sex"
													type="radio" name="sex" value="女" />女

												</label> <label class="uniform"> <input class="sex"
													type="radio" name="sex" value="保密" />保密

												</label>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">生日</label>
											<div class="col-lg-10">
												<input type="text" name="birthdayStr"
													class="form-control datepicker" id="birthdayStr"
													value="${user.birthday }">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">行业</label>
											<div class="col-lg-10">
												<select name="parentTypeId" id="parentTypeId"
													class="form-control">

												</select> <span class="help-block"></span>
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">手机</label>
											<div class="col-lg-10">

												<input name="phone" type="text" value="${user.phone }"
													class="form-control col-md-6" id="phone">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">邮箱</label>
											<div class="col-lg-10">

												<input name="email" type="text" value="${user.email }"
													class="form-control col-md-6" id="email">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">QQ</label>
											<div class="col-lg-10">

												<input name="QQ" type="text" value="${user.QQ }"
													class="form-control col-md-6" id="QQ">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">所属地区</label>
											<div class="col-lg-3">
												<select name="province" id="province" class="form-control">
													 
												</select>
											</div>
											<div class="col-lg-3">
												<select name="city" id="city" class="form-control">
													 
												</select>
											</div>
											<div class="col-lg-3">
												<select name="district" id="district" class="form-control">
													 
												</select>
											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">详细地址</label>
											<div class="col-lg-10">

												<input name="address" type="text" value="${user.address }"
													class="form-control col-md-6" id="address">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">个人介绍</label>
											<div class="col-lg-10">

												<input name="introduce" type="text" value="${user.introduce }"
													class="form-control col-md-6" id="address">
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

 

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script type="text/javascript">
	$(function() {
		getParentType();
		new PCAS("province", "city", "district", "", "", "");

		$('.datepicker').datepicker({
			format : 'yyyy-mm-dd',
			weekStart : 1,
			autoclose : true,
			todayBtn : 'linked'
		});
		
		init();
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
	
	function selectIns(name, value, text) {
		var selObj = $(name);
		selObj.prepend("<option value='"+value+"' selected='selected'>" + text + "</option>");
	}
	
	function init()
	{
		var p = '${user.province}';
		var c = '${user.city}';
		var d = '${user.district}';
		
		if(p!='')
		{
			selectIns('#province',p,p);
		}
		
		if(c!='')
		{
			selectIns('#city',c,c);
		}
		
		if(d!='')
		{
			selectIns('#district',d,d);
		}
	}
	
	
	
</script>