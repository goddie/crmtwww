<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />

<c:forEach var="m" varStatus="status" items="${list}">
<div class="col-xs-12 col-sm-3">
	<dl class="o-dllist">
		<dt>
			<a href="${pageContext.request.contextPath}/product/detail?pid=${m.id}" target="_blank"><img
				src="${m.thumb }"></a>
		</dt>
		<dd>
			<a href="${pageContext.request.contextPath}/product/detail?pid=${m.id}" target="_blank">${m.name }</a>
		</dd>
		<dd>
			<span class="iconVideo"></span><span class="f_c_fd0100">${m.price }</span>
		</dd>
		<dd>
			最近<span class="f_c_fd0100">${m.tradeCount }</span>人购买
		</dd>
	</dl>
</div>
</c:forEach>