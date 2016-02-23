<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />
<div class="container">
		<div class="row">
			<div class="col-md-12 ">

				<div class="m-headline" id="header_top_fixed">
					<div class="m-warp">
						<div class="m-headline-l">
							<h2 class="m-headline-caption">
								<a target="_blank"
									href="${pageContext.request.contextPath}/webpage/newslist">
									<span>今日头条</span>
								</a>
							</h2>
							
							<c:forEach var="m" varStatus="status" items="${list1}">
							<a target="_blank"
								href="${pageContext.request.contextPath}/article/detail?id=${m.id}"
								class="m-headline-img"> <img class="l-img"
								src="${util.getCover(m.thumb,0) }"
								alt="" width="748" height="246"> <span class="m-headline-w"> <span
									class="m-headline-w-bg"></span> <b class="m-headline-w-c">${m.title }</b>
							</span>
							</a>
							
							</c:forEach>
							
							
							
						</div>
						<div class="m-headline-r">
							<a target="_blank" href="${pageContext.request.contextPath}/webpage/newslist"
								class="m-more">更多&gt;&gt;</a>
							<h2 class="m-headline-caption">
								<a target="_blank" href="${pageContext.request.contextPath}/webpage/newslist">
									<span>新闻</span>中心
								</a>
							</h2>
							
							<c:forEach var="m" varStatus="status" items="${list2}">
							<a target="_blank"
								href="${pageContext.request.contextPath}/article/detail?id=${m.id}"
								class="m-headline-dl">
								<div class="dt">
									<img
										src="${m.thumb }"
										alt="" class="r-img">
								</div>
								<div class="dd">
									<p class="m-headline-title" title="“${m.title }">${m.title }</p>
									<p class="m-headline-time">
										<i class="m-icon m-icon-arrow-right"></i>${m.createdDate }
									</p>
								</div>
							</a>
							
							</c:forEach>
							
							   
						</div>
					</div>
				</div>


			</div>

		</div>
	</div>