<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="eutil" class="com.xiaba2.util.EnumUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投稿列表</title>
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
								&nbsp;

							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row" style="display: none">

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
												<th class="sorting_asc" style="widht: 5%">序号</th>

												<th role="columnheader"
													aria-label="Browser: activate to sort column ascending"
													style="">任务</th>
													<th role="columnheader">任务类型</th>
												 
												<th role="columnheader">投稿时间</th>
												<th role="columnheader">投稿数</th>
												<th role="columnheader">中标人</th>
												<th role="columnheader">赏金</th>
												<th role="columnheader">标书状态</th>
												<th role="columnheader">任务状态</th>
												<th role="columnheader" style="width: 140px;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<c:forEach var="m" varStatus="status" items="${list}">
												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>

													<td class="">${m.task.title }</td>
													<td class="">${eutil.getTaskType(m.topType) }</td>
													 
													<td class="">${m.createdDate }</td>
													<td class="center ">${m.task.submitCount }</td>
													<td class="center ">${m.task.win.nickname}</td>
													<td class="center ">${m.task.bounty}</td>
													<td class="center "><c:if test="${m.isWin==1 }">中标</c:if><c:if test="${m.isWin!=1 }">未中标</c:if></td>
													<td class="center ">${eutil.getTaskStatusName(m.task.status) }</td>
												
													<td class="action">
													<c:if test="${m.isWin!=1 }">
													<a class="btn btn-xs btn-primary"
														href="${pageContext.request.contextPath}/submit/v/useradd?taskId=${m.task.id}">
															编辑查看</a> <a class="btn btn-xs btn-primary"
														href="${pageContext.request.contextPath}/submit/action/del?uuid=${m.id}">
															删除 </a>
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