<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page"/>  
<div class="col-xs-12">
	<div id="playBox" style="height: 452px;">
		<div class="pre"></div>
		<div class="next"></div>
		<div class="smalltitle">
			<ul>
				<li class="thistitle"></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<ul class="oUlplay">

			<c:forEach var="m" varStatus="status" items="${list}">
				<li><a href="${m.redirectUrl }" target="_blank"><img
						src="${util.getOrigin(m.thumb) }"></a></li>
			</c:forEach>


		</ul>
	</div>
</div>