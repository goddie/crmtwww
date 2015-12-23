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
<body>
 

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
								<div class="text-muted bootstrap-admin-box-title">分类列表</div>

							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

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
												<th role="columnheader" style="width: 10%;">序号</th>

												<th role="columnheader">名称</th>
												<th role="columnheader">父级</th>
												<th role="columnheader" style="width: 20%;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="" title="${m.id }">${m.name}</td>
													<td class="">${m.parent.name}</td>

													<td class="action"><a
														href="${pageContext.request.contextPath}/forumtype/action/del?fid=${m.id}">
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