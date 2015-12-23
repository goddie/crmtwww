<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="eutil" class="com.xiaba2.util.EnumUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值记录</title>
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
								<div class="text-muted bootstrap-admin-box-title">充值记录</div>
								 
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th class="sorting_asc" role="columnheader"
													aria-sort="ascending"
													aria-label="Rendering engine: activate to sort column descending"
													style="widht: 5%">序号</th>
												<th role="columnheader" style="">充值日期</th>
												 
												<th role="columnheader">交易金额</th>
 									 
												<th role="columnheader" style="width: 140px;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<c:forEach var="m" varStatus="status" items="${list}">
												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="">${m.createdDate}</td>
												 
												 
													 <td class="center ">${m.total}</td>
											 
													<td class="action"><a style="display: none;">编辑 </a> </td>
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