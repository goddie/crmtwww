<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />
<div class="col-xs-12 col-sm-9 p_b20">
	<div class="m_r20">
		<div class="tab">
			<a id="id0" onclick="showTab_b(0)" class="orangeborder">创意分享汇</a>|<a
				id="id1" onclick="showTab_b(1)" class="noborder">创意大师馆</a>|<a
				id="id2" onclick="showTab_b(2)" class="noborder">创意专题馆</a>|<a
				id="id3" onclick="showTab_b(3)" class="noborder">创意嘉年华</a>|<a
				id="id4" onclick="showTab_b(4)" class="noborder">创意征集令</a>
		</div>



		<div class="tab_content" id="tab0" style="display: block;">
			<ul class="detaillist">

				<c:forEach var="m" varStatus="status" items="${list1}">
					<li><span class="img-t"><a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}">
									<c:if test="${m.thumb!=null && m.thumb!=''}">
									<img src=" ${m.thumb}"
							width="181" height="114"/>
									</c:if>
									
									<c:if test="${m.thumb==null || m.thumb=='' }">
									<img src="${pageContext.request.contextPath}/resource/web/images/nopic_232.png"
							width="181" height="114"/>
									</c:if>
									</a> </span>
						<dl class="info">
							<dt>
								<span class="f_c_0a538e f20"><a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}">${m.title }</a></span>
							</dt>
							<dd>
								<span class="f_c_6cbef5"><a href="${pageContext.request.contextPath}/user/usersite?uuid=${entity.user.id}" target="_blank">${m.user.nickname }</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.createdDate }
							</dd>
							<dd class="p_t20b25">${ util.getSubContent(m.content,200) }
							<dd>
								<a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}"
									class="f_c_6cbef5">查看全文 》</a>
							</dd>
						</dl></li>
				</c:forEach>


			</ul>

		</div>
		<div class="tab_content" id="tab1">
			<ul class="detaillist">
				<c:forEach var="m" varStatus="status" items="${list2}">
					<li><span class="img-t"><img src=" ${m.thumb}"
							width="181" height="114"> </span>
						<dl class="info">
							<dt>
								<span class="f_c_0a538e f20">${m.title }</span>
							</dt>
							<dd>
								<span class="f_c_6cbef5">${m.user.nickname }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.createdDate }
							</dd>
							<dd class="p_t20b25">${ util.getSubContent(m.content,200) }<dd>
								<a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}"
									class="f_c_6cbef5">查看全文 》</a>
							</dd>
						</dl></li>
				</c:forEach>
			</ul>

		</div>
		<div class="tab_content" id="tab2">
			<ul class="detaillist">
				<c:forEach var="m" varStatus="status" items="${list3}">
					<li><span class="img-t"><img src=" ${m.thumb}"
							width="181" height="114"> </span>
						<dl class="info">
							<dt>
								<span class="f_c_0a538e f20">${m.title }</span>
							</dt>
							<dd>
								<span class="f_c_6cbef5">${m.user.nickname }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.createdDate }
							</dd>
							<dd class="p_t20b25">${ util.getSubContent(m.content,200) }<dd>
								<a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}"
									class="f_c_6cbef5">查看全文 》</a>
							</dd>
						</dl></li>
				</c:forEach>
			</ul>

		</div>
		<div class="tab_content" id="tab3">
			<ul class="detaillist">
				<c:forEach var="m" varStatus="status" items="${list4}">
					<li><span class="img-t"><img src=" ${m.thumb}"
							width="181" height="114"> </span>
						<dl class="info">
							<dt>
								<span class="f_c_0a538e f20">${m.title }</span>
							</dt>
							<dd>
								<span class="f_c_6cbef5">${m.user.nickname }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.createdDate }
							</dd>
							<dd class="p_t20b25">${ util.getSubContent(m.content,200) }<dd>
								<a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}"
									class="f_c_6cbef5">查看全文 》</a>
							</dd>
						</dl></li>
				</c:forEach>
			</ul>

		</div>
		<div class="tab_content" id="tab4">
			<ul class="detaillist">
				<c:forEach var="m" varStatus="status" items="${list5}">
					<li><span class="img-t"><img src=" ${m.thumb}"
							width="181" height="114"> </span>
						<dl class="info">
							<dt>
								<span class="f_c_0a538e f20">${m.title }</span>
							</dt>
							<dd>
								<span class="f_c_6cbef5">${m.user.nickname }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${m.createdDate }
							</dd>
							<dd class="p_t20b25">${ util.getSubContent(m.content,200) }<dd>
								<a
									href="${pageContext.request.contextPath}/forum/detail?id=${m.id}"
									class="f_c_6cbef5">查看全文 》</a>
							</dd>
						</dl></li>
				</c:forEach>
			</ul>

		</div>
	</div>
</div>