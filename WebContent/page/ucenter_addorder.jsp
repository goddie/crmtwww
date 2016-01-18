<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>购买提交</title>
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
								<div class="text-muted bootstrap-admin-box-title">订单信息</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">订单编号</label>
											<label class="col-lg-2 control-label">${order.orderNum }</label>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">商品名称</label>
											<label class="col-lg-2 control-label">${product.name }</label>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">价格</label>
											<label class="col-lg-2 control-label">${product.price }</label>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">卖家名称</label>
											<label class="col-lg-2 control-label">${product.user.nickname }</label>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">应付金额</label>
											<label class="col-lg-2 control-label"><span
												style="color: #f00">${order.total }</span></label>
										</div>
									</fieldset>

								</form>

							</div>
						</div>

						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">收款信息</div>
								<span style="color: #f00">(凡购买创意商品，请买家自行和卖家联系，自行支付到卖家提供的帐号里)</span>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/product/action/finishorder"
									name="form1" method="post" class="form-horizontal">
									<input type="hidden" name="orderId" id="orderId" value="${order.id}" />
										<fieldset>
											<div class="form-group">
												<label class="col-lg-2 control-label" for="typeahead">收款人账号</label>
												<label class="col-lg-2 control-label">${product.bankNo }</label>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label" for="typeahead">银行</label>
												<label class="col-lg-2 control-label">${product.bankName }</label>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label" for="typeahead">收款人姓名</label>
												<label class="col-lg-2 control-label">${product.bankAccount }</label>
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
