<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">个人实名审核</div>

							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

										<div class="col-md-6" style="display:none">
											<div class="dataTables_filter" id="example_filter">
												<label>Search: <input type="text"
													aria-controls="example"></label>
											</div>
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 10%;">序号</th>

												
												<th role="columnheader">登录名</th>
												<th role="columnheader">用户昵称</th>
												<th role="columnheader">注册日期</th>
												<th role="columnheader">实名认证</th>
												<th role="columnheader" style="width: 20%;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class=""><a href="${pageContext.request.contextPath}/user/usersite?uuid=${m.id}" target="_blank">${m.username}</a></td>
													<td class="">${m.nickname}</td>
													<td class="">${m.createdDate}</td>
													<td class="">${m.createdDate}</td>
													<td class="action"><a class="btn btn-xs btn-primary" onclick="return confirm('确认通过?')"
														href="${pageContext.request.contextPath}/user/action/review?id=${m.id}&rs=1&type=1">
															通过</a> <a class="btn btn-xs btn-primary" onclick="return confirm('确认失败?')"
														href="${pageContext.request.contextPath}/user/action/review?id=${m.id}&rs=10&type=1">
															失败</a> 
															<a class="btn btn-xs btn-primary" 
														href="${pageContext.request.contextPath}/user/admin/persondetail?id=${m.id}">
															查看</a>
															</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">

										${pageHtml }
									</div>
								</div>
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