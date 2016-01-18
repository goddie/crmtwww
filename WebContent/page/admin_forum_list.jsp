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
								<div class="text-muted bootstrap-admin-box-title">帖子列表</div>

							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row" style="display:none;">

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

												<th role="columnheader">标题</th>
												<th role="columnheader">分类</th>
												<th role="columnheader">作者</th>
												<th role="columnheader">时间</th>
												<th role="columnheader">审核</th>
												<th role="columnheader" style="width: 20%;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="" title="${m.id }">${m.title}</td>
													<td class="">${m.type.name}-${m.subType.name}</td>
													<td class="">${m.user.nickname}</td>
													<td class="">${m.createdDate}</td>
													<td class=""><c:if test="${m.isCheck==1 }">通过</c:if> <c:if
															test="${m.isCheck==0 }">待审核</c:if><c:if
															test="${m.isCheck==2 }">拒绝</c:if></td>
													<td class="action"><a class="btn btn-xs btn-primary"
														onclick="return confirm('确认删除?')"
														href="${pageContext.request.contextPath}/forum/action/del?id=${m.id}">
															删除</a>  
															
															<c:if test="${m.isCheck==0 }">
															<a class="btn btn-xs btn-primary"
																href="${pageContext.request.contextPath}/forum/action/ischeck?id=${m.id}&rs=1">
																通过</a>
													  
															<a class="btn btn-xs btn-primary"
																href="${pageContext.request.contextPath}/forum/action/ischeck?id=${m.id}&rs=2">
																拒绝</a>
															</c:if>
															
														 </td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">${pageHtml }</div>
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