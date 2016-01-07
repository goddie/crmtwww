<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收件箱</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
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
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">收件箱</div>
								<a href="${pageContext.request.contextPath}/message/v/add"
									class="btn btn-xs btn-primary">写消息</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row" style="display: none;">

										<div class="col-md-6">
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
												<th class="sorting_asc" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Rendering engine: activate to sort column descending"
													style="width: 5%;">序号</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 10%;">收件人</th>
												<th role="columnheader">标题</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="Engine version: activate to sort column ascending">内容</th>


												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="CSS grade: activate to sort column ascending">时间</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="CSS grade: activate to sort column ascending"
													style="width: 140px;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="">${m.sendTo.username}</td>
													<td class="">${m.title}</td>
													<td class="center ">${m.content}</td>
													<td class="center ">${m.createdDate}</td>
													<td class="action"><a style="display: none;"
														href="${pageContext.request.contextPath}/message/edit/${m.id}">
															编辑 </a> <a
														class="btn btn-xs btn-primary" onclick="return confirm('确认删除?')"
														href="${pageContext.request.contextPath}/message/action/del?id=${m.id}">
															删除 </a></td>
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