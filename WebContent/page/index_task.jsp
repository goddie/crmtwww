<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="util" class="com.xiaba2.util.HttpUtil" scope="page" />
<div class="c-task">
	<div class="task-menu m_tb16 clearfix">
		<div class="m_b16 clearfix">
			<h2 class="c-t-title fl">任务大厅</h2>
			<div class="c-t-btn fr">
				<div class="searchbg clearfix">
					<form action="${pageContext.request.contextPath}/task/search"
						method="get" name="form1">
						<input name="key" type="text" placeholder="快速搜索"><span onclick="form1.submit();"
							class="iconsearch"></span>
					</form>

				</div>
				<a href="${pageContext.request.contextPath}/task/v/add?type=1"
					target="_blank" class="grayBtn1a">发任务</a> <a
					href="${pageContext.request.contextPath}/task/index"
					target="_blank" class="grayBtn2a">找任务</a>
			</div>
		</div>
		<div class="c-hall-lst">
			<div class="c-warp" style="height: 990px;">
				<ul class="c-hall-carousel task-carousel">
					<li><a target="_blank"
						href="${pageContext.request.contextPath}/task/v/add?type=1"
						class="c-hall-logo release-logo" style="left: 0; top: 0;"><img
							src="${pageContext.request.contextPath}/resource/web/images/icon_gongju1.png"
							width="71" height="71" /> <br /> 发任务 </a>
						<div
							style="position: absolute; left: 254px; top: 0; width: 692px; height: 492px;">
							<a target="_blank"
								href="${pageContext.request.contextPath}/task/detail?tid=${list[0].id}"
								class="c-hall-lst-goods" style="position: relative;"> <img
								class="lazy" src="${util.getCover(list[0].thumb,0) }"
								width="692" height="492">
							</a> <span class="c-hall-lst-goods-info">
								<p class="c-info-t">${list[0].title}</p>
								<p class="c-info-t">${list[0].bountyPrice}</p>
								<div class="new-c-personal">
									<div class="new-c-photo">
										<a target="_self"
											href="${pageContext.request.contextPath}/user/usersite?uuid=990c9e9c-b743-4c10-9dcd-4cf1a256c56a">
											<img src="${list[0].user.head}"
											style="box-sizing: content-box; -moz-box-sizing: content-box; -ms-box-sizing: content-box; width: 31px; height: 31px; border-radius: 15px 15px;">
										</a>
									</div>
									<p>
										<a target="_blank"
											href="${pageContext.request.contextPath}/user/usersite?uuid=${list[0].user.id }">${list[0].user.nickname}</a>
									</p>
								</div>
							</span>
						</div> <a class="c-hall-logo release-task"
						style="left: 962px; top: 254px;">
							<p class="release-task-t">
								<i class="c-icon-l c-icon-task-s"></i>任务大厅
							</p> <span class="release-task-txt"> 用户发布和承接任务的专业
								平台，支持创意悬赏和项目定制的在线 分包与承接，提供更多创意选择和更多 工作机会。 </span>
					</a> <a target="_blank"
						href="${pageContext.request.contextPath}/task/detail?tid=${list[1].id}"
						class="c-hall-lst-goods"
						style="left: 0px; top: 254px; width: 238px; height: 492px;"> <img
							src="${util.getOrigin(list[1].thumb) }" width="238" height="492" />
							<span class="c-hall-lst-goods-info1"
							style="background-color: #baa298;">
								<p class="c-info-t">${list[1].title}</p>
								<p class="c-info-txt">${list[1].bountyPrice}</p>
								<p class="c-info-txt">
									<a target="_blank"
										href="${pageContext.request.contextPath}/user/usersite?uuid=${list[1].user.id }">${list[1].user.nickname}</a>
								</p>
						</span>
					</a> <a target="_blank"
						href="${pageContext.request.contextPath}/task/detail?tid=${list[2].id}"
						class="c-hall-lst-goods"
						style="left: 254px; top: 508px; width: 238px; height: 238px;">
							<img class="lazy" src="${util.getOrigin(list[2].thumb) }"
							data-original="images/pic3.jpg" width="238" height="238">
							<div class="c-hall-lst-goods-bg"></div>
							<div class="c-hall-lst-goods-word">
								<div class="table-cell">
									<p class="c-hall-lst-goods-title">${list[2].title}</p>
									<p class="c-hall-lst-goods-price">${list[2].bountyPrice}</p>
									<p class="c-info-txt">
										<a target="_blank"
											href="${pageContext.request.contextPath}/user/usersite?uuid=${list[2].user.id }">${list[2].user.nickname}</a>
									</p>
								</div>
							</div>
					</a> <a target="_blank"
						href="${pageContext.request.contextPath}/task/detail?tid=${list[3].id}"
						class="c-hall-lst-goods"
						style="left: 0px; top: 762px; width: 492px; height: 238px;"> <img
							class="lazy" src="${util.getOrigin(list[3].thumb) }"
							data-original="images/pic5.jpg" width="492" height="238">
							<div class="c-hall-lst-goods-bg"></div>
							<div class="c-hall-lst-goods-word">
								<div class="table-cell">
									<p class="c-hall-lst-goods-title">${list[3].title}</p>
									<p class="c-hall-lst-goods-price">${list[3].bountyPrice}</p>
									<p class="c-info-txt">
										<a target="_blank"
											href="${pageContext.request.contextPath}/user/usersite?uuid=${list[3].user.id }">${list[3].user.nickname}</a>
									</p>
								</div>
							</div>
					</a>
						<div
							style="position: absolute; left: 508px; top: 508px; width: 692px; height: 492px;">
							<a target="_blank"
								href="${pageContext.request.contextPath}/task/detail?tid=${list[4].id}"
								class="c-hall-lst-goods" style="position: relative;"> <img
								class="lazy" src="${util.getOrigin(list[4].thumb) }"
								data-original="images/pic4.jpg" width="692" height="492">
							</a> <span class="c-hall-lst-goods-info"
								style="background-color: #9ca299;">
								<p class="c-info-t">${list[4].title}</p>
								<p class="c-info-t">${list[4].bountyPrice}</p>
								<div class="new-c-personal">
									<div class="new-c-photo">
										<a target="_self" href="#"> <img
											src="${list[4].user.head}"
											style="box-sizing: content-box; -moz-box-sizing: content-box; -ms-box-sizing: content-box; width: 31px; height: 31px; border-radius: 15px 15px;">
										</a>
									</div>
									<p>
										<a target="_self" href="#">${list[4].user.nickname}</a>
									</p>
								</div>
							</span>
						</div> <a href="${pageContext.request.contextPath}/task/index"
						class="c-hall-pager-new i-next">找任务<br /> <img
							src="${pageContext.request.contextPath}/resource/web/images/icon_gongju2.png"
							width="88" height="75" /></a></li>
				</ul>
			</div>
		</div>
	</div>
</div>