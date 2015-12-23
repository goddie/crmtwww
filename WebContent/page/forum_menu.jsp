<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="graybg2">
	<ul class="c-list01">


		<c:forEach var="m" varStatus="status" items="${list}">

			<li>
				<dl>
					<dt>${m.parent.name }</dt>
					<dd>

						<c:forEach var="s" varStatus="status" items="${m.sub}">
							<li><a href="${pageContext.request.contextPath}/forum/sub?id=${s.id}" target="_blank">${s.name }</a></li>
						</c:forEach>

					</dd>
				</dl>
			</li>

		</c:forEach>



	</ul>
</div>