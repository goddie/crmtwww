<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建文章</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body  class="bootstrap-admin-with-small-navbar">
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
								<div class="text-muted bootstrap-admin-box-title">导入帐号</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/user/importaccount"
									name="form1" method="post" class="form-horizontal" enctype="multipart/form-data">
									<input name="topType" value="${topType }" type="hidden" />
									<fieldset>
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">CSV文件</label>
											<div class="col-lg-10">
												<input name="file" type="file"
													/>
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
