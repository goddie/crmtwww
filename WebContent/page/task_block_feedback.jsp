<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="m" varStatus="status" items="${list}">
	<li><span class="img-t"><img src="${m.author.head }"
			width="79" height="79"> </span>
		<dl class="info">
			<dt class="clearfix">
				<span class="fl f_c_6cbef5">${m.author.nickname }</span><span
					class="fr f_c_a1a1a1">${m.createdDate }</span>
			</dt>
			<dd class="p_b20">${m.content }</dd>
			<dd style="display: none;">
				<a href="#" class="blueBtn1">评论</a><a href="#"
					class="blueBtn1 m_l30r40">举报</a>&nbsp;买家已浏览
			</dd>
		</dl></li>
</c:forEach>