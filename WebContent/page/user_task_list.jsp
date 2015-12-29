<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="eutil" class="com.xiaba2.util.EnumUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务列表</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<c:import url="/user/usernav" />

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_task.jsp"></jsp:include>
			</div>
			<div class="col-md-10">

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">${topName }</div>
								&nbsp; <a
									href="${pageContext.request.contextPath}/task/v/add?type=${topType}"
									class="btn btn-xs btn-primary">发布任务</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">
										<div class="col-md-6">
											<div id="example_length" class="dataTables_length">
												<label><select size="1" name="example_length"
													aria-controls="example"><option value="10"
															selected="selected">10</option>
														<option value="25">25</option>
														<option value="50">50</option>
														<option value="100">100</option></select> records per page</label>
											</div>
										</div>
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
												<th class="sorting_asc" role="columnheader"
													aria-sort="ascending"
													aria-label="Rendering engine: activate to sort column descending"
													style="widht: 5%">序号</th>
												<th role="columnheader" style="">类型</th>
												<th role="columnheader" style="width: 224px;">任务</th>
												<th role="columnheader">赏金</th>
												<th role="columnheader">投稿数</th>
												<th role="columnheader">中标人</th>
												<th role="columnheader">状态</th>
												<th role="columnheader">发布时间</th>
												<th role="columnheader" style="width: 140px;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<c:forEach var="m" varStatus="status" items="${list}">
												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class=""><span title="${m.parentType.id }">${m.parentType.name }</span>/<span
														title="${m.subType.id }">${m.subType.name }</span></td>
													<td class=""><a href="${pageContext.request.contextPath}/task/detail?tid=${m.id}">${m.title }</a></td>
													<td class="">${m.bountyPrice }</td>
													<td class="center ">${m.submitCount}</td>
													<td class="center ">${m.win.user.username }</td>
													<td class="center ">${eutil.getTaskStatusName(m.status) }</td>
													<td class="center ">${m.createdDate }</td>
													<td class="action"><a style="display: none;">编辑 </a> <a
														class="btn btn-xs btn-primary"
														href="${pageContext.request.contextPath}/task/action/del?id=${m.id}">
															删除 </a> 
															<c:if test="${m.status!=0&&m.status!=10 }">
															<a class="btn btn-xs btn-primary"
														href="${pageContext.request.contextPath}/submit/review?taskId=${m.id}">
															选稿</a> <a class="btn btn-xs btn-primary"
														href="${pageContext.request.contextPath}/task/detail?tid=${m.id}" target="_blank">
															查看</a>
															</c:if>
															</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">
 
										<div class="col-md-12">
											 ${p }
										</div>
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