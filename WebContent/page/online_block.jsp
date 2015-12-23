<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />

<c:forEach var="m" varStatus="status" items="${list}">
<div class="col-xs-12 col-sm-3">
	<dl class="o-dllist">
		<dt>
			<a href="https://cloud3.yxhltech.com"><img
				src="${m.thumb }"></a>
		</dt>
		<dd>
			<a href="#">${m.title }</a>
		</dd>
		<dd>
			<span class="iconVideo"></span><span class="f_c_fd0100">免费</span>
		</dd>
		<dd>
			最近<span class="f_c_fd0100">${m.visits }</span>人使用
		</dd>
	</dl>
</div>
</c:forEach>