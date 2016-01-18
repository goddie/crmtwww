<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="eutil" class="com.xiaba2.util.EnumUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我发布的</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<c:import url="/user/usernav" />

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_product.jsp"></jsp:include>
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
									<div class="row">

										<div class="col-md-6">
											<div class="dataTables_filter" id="example_filter"
												style="display: none">
												<label>Search: <input type="text"
													aria-controls="example"></label>
											</div>
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 5%;">序号</th>
												<th role="columnheader" style="">名称</th>
												<th role="columnheader">类别</th>
												<th role="columnheader">类型</th>
												<th role="columnheader">价格</th>
												<th role="columnheader">审核状态</th>
												<th role="columnheader">销售状态</th>
												<th role="columnheader">发布时间</th>
												<th role="columnheader" style="width: 140px;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<c:forEach var="m" varStatus="status" items="${list}">
												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class=""><a target="_blank"
														href="${pageContext.request.contextPath}/product/detail?pid=${m.id }">${m.name }</a></td>
													<td class="">${eutil.getProductTopType(m.topType) }</td>
													<td class="center ">${m.parentType.name}-${m.subType.name}</td>
													<td class="center ">${m.price }</td>
													<td class="center ">${eutil.getProductStatus(m.status) }</td>
													<td class="center "><c:if test="${m.isOnSale==1}">上架</c:if>
														<c:if test="${m.isOnSale==0}">下架</c:if></td>
													<td class="center ">${m.createdDate }</td>
													<td class="action" title="${m.id }"><c:if
															test="${m.status!=1 }">
															<a class="btn btn-xs btn-primary"
																href="${pageContext.request.contextPath}/product/v/edit?id=${m.id}">
																编辑</a>
															<a class="btn btn-xs btn-primary"
																onclick="return confirm('确认删除?')"
																href="${pageContext.request.contextPath}/product/action/del?id=${m.id}&rs=0">
																删除 </a>
														</c:if> <c:if test="${m.status==1 }">
															<a style="display:none" class="btn btn-xs btn-primary"
																href="${pageContext.request.contextPath}/product/v/edit?id=${m.id}">
																编辑</a>



															<c:if test="${m.isOnSale==0 }">
																<a class="btn btn-xs btn-primary"
																	onclick="return confirm('确认上架?')"
																	href="${pageContext.request.contextPath}/product/action/onsale?id=${m.id}&rs=1">
																	上架</a>
															</c:if>
															<c:if test="${m.isOnSale==1}">
																<a class="btn btn-xs btn-primary"
																	onclick="return confirm('确认下架?')"
																	href="${pageContext.request.contextPath}/product/action/onsale?id=${m.id}&rs=0">
																	下架 </a>
															</c:if>





														</c:if></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">${page }</div>
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