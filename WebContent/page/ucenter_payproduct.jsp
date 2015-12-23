<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>支付商品</title>
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
								<div class="text-muted bootstrap-admin-box-title">支付商品</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/product/action/payproduct?orderId=${orderId}" name="form1" method="post" class="form-horizontal">
									
									<fieldset>
									<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">支付商品</label>
											<label class="col-lg-2 control-label">${product.name }</label>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">账户余额</label>
											<label class="col-lg-2 control-label">${user.money }</label>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">支付金额</label>
											<label class="col-lg-2 control-label">${toPay }</label>
											<input name="money" id="money" value="${toPay} }" type="hidden" />

										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">支付方式</label>
											<div class="col-lg-10">
												 <select id="payType" name="payType" class="chzn-select" style="width: 150px">
                                                        <option>余额支付</option>
                                                        <option>支付宝</option>
                                                        <option>微信支付</option>
                                                    </select>
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