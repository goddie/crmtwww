<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>潮人码头 - 文化产业电子商务平台 - 个人中心</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
<script type="text/javascript">
	clickMenu = function(menu, element, cname) {
		var getEls = document.getElementById(menu)
				.getElementsByTagName(element);

		for (var i = 0; i < getEls.length; i++) {
			getEls[i].onclick = function() {
				if ((this.className.indexOf(cname)) != -1) {
					if ((this.className.indexOf('click')) != -1) {
						this.className = this.className.replace("click", "");
						;
					} else {
						this.className += " click";
					}
				}
			}
		}
	}

	function showTab_c(num) {
		for (i = 0; i < 3; i++) {
			document.getElementById("tab0" + i).style.display = "none";
			document.getElementById("id0" + i).className = "";

		}
		document.getElementById("tab0" + num).style.display = "block";
		document.getElementById("id0" + num).className = "active";
	}

	function showTabs(num) {
		for (i = 0; i < 2; i++) {
			document.getElementById("tabs" + i).style.display = "none";
			document.getElementById("ids" + i).className = "";

		}
		document.getElementById("tabs" + num).style.display = "block";
		document.getElementById("ids" + num).className = "active";
	}

	function showTabs0(num) {
		for (i = 0; i < 2; i++) {
			document.getElementById("tabs0" + i).style.display = "none";
			document.getElementById("ids0" + i).className = "";

		}
		document.getElementById("tabs0" + num).style.display = "block";
		document.getElementById("ids0" + num).className = "active";
	}
</script>


</head>
<body onload="clickMenu('outer','div','w-folding-up-down')">
	<!--nav-->
	<c:import url="/webpage/webnav" />
	<div class="graybg4">
		<div class="container">
			<div class="row row-offcanvas row-offcanvas-right">
				<div class="col-xs-12">
					<div class="userPicBg">
						<div class="userPic">
							<a href="#" target="_self"><img src="${user.head }"
								width="200" height="200"></a>
						</div>
					</div>
				</div>
				<div class="tac">
					<div class="col-xs-12 col-sm-4">
						<span class="f_c_f3711a f60">${user.visits }</span>
						<p class="p_b10 f16">浏览次数</p>
					</div>
					<div class="col-xs-12 col-sm-4 p_t10">
						<span class="f36 p_b10">${user.nickname }</span><span
							class="f_c_a5c4f2 p_b10">${user.introduce }</span><br /> <span
							class="u-name"><a href="javascript:void(0)"
							onclick="addFollow()" class="u-focus">关注我</a><a
							href="${pageContext.request.contextPath}/message/v/add?sendTo=${user.username}"
							class="u-message" target="_blank">私信我</a></span>
					</div>
					<div class="col-xs-12 col-sm-4">
						<span class="f_c_f3711a f60">${user.followCount }</span>
						<p class="p_b10 f16">我的粉丝</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="graybg4">
		<div class="container">
			<div class="u-tag-title">
				<ul>
					<li class="u-title-300"><a id="id00" onclick="showTab_c(0)"
						href="javascript:void(0);">作品集</a></li>
					<li class="u-title-500"><a id="id01" onclick="showTab_c(1)"
						class="active" href="javascript:void(0);">用户经历简介</a></li>
					<li class="u-title-300"><a id="id02" onclick="showTab_c(2)"
						href="javascript:void(0);">用户交易记录</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="u-graybg">
		<div class="container">
			<div class="col-xs-12 m_t20">
				<div class="tab_content" id="tab00">

					<c:if test="${worklist==null }">
						<!--无作品-->
						<ul class="worklist nowork clearfix">
							<li>
								<div class="workinfo">
									<span class="noworkPic"></span> <span>暂无作品</span>
								</div>
							</li>
						</ul>
					</c:if>

					<c:if test="${worklist!=null }">
						<!--有作品-->
						<ul class="worklist clearfix">
							<c:forEach var="m" varStatus="status" items="${worklist}">
								<li><a title="${m.title }"
									href="${pageContext.request.contextPath}/work/detail?id=${m.id}"
									target="_blank"><img src="${m.thumb }" width="100%"
										height="192px"></a></li>
							</c:forEach>

						</ul>
					</c:if>
				</div>
				<div class="tab_content" id="tab01" style="display: block;">
					<dl class="uDllist">
						<dt>个人信息</dt>
						<dd>
							<span>所在地区<br /> ${user.city }
							</span> <span>腾讯QQ<br /> ${user.QQ }
							</span> <span>认证状态<br /> <c:if test="${user.isCheckPerson==1 }">已通过个人认证</c:if>
								<c:if test="${user.isCheckCompany==1 }">已通过企业认证</c:if></span>
						</dd>
						<dd>
							<span>昵称<br /> ${user.nickname }
							</span> <span>手机<br /> ${user.phone }
							</span> <span>账户类型<br /> 个人用户
							</span>
						</dd>
					</dl>
					<dl class="uDllist">
						<dt>主要介绍</dt>
						<dd>${user.introduce }</dd>
					</dl>
					<div id="outer" style="display: none">
						<div class="title">项目经历</div>
						<div class="w-folding-up-down">
							<a class="zk" href="javascript:void(0);"></a> <a class="gb"
								href="javascript:void(0);"></a>
							<div class="w-tab-content w-table-record">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<th>项目名称</th>
											<th>开始时间</th>
											<th>结束时间</th>
											<th>所负责的工作内容</th>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

						<div class="title">工作经历</div>
						<div class="w-folding-up-down">
							<a class="zk" href="javascript:void(0);"></a> <a class="gb"
								href="javascript:void(0);"></a>
							<div class="w-tab-content w-table-record">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<th>公司名称</th>
											<th>职位</th>
											<th>开始时间</th>
											<th>结束时间</th>
											<th>主要工作内容</th>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

						<div class="title">教育或培训经历</div>
						<div class="w-folding-up-down">
							<a class="zk" href="javascript:void(0);"></a> <a class="gb"
								href="javascript:void(0);"></a>
							<div class="w-tab-content w-table-record">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<th>教育或培训机构名称</th>
											<th>开始时间</th>
											<th>结束时间</th>
											<th>学历或所获资质</th>
											<th>教育专业或培训技能</th>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<dl class="uDllist" style="display: none">
						<dt>证书</dt>
						<dd>
							<a href="#">
								<div class="zs">
									<p>暂无证书</p>
								</div>
							</a>
						</dd>
					</dl>
					<dl class="uDllist noborder" style="display: none">
						<dt>擅长技能</dt>
						<dd></dd>
					</dl>
				</div>
				<div class="tab_content" id="tab02">
					<dl class="uDllist">
						<dt>交易评价</dt>
						<dd>
							<div class="w-evaluation-box">
								<div class="w-evaluation-box_1">
									<div class="w-evaluation-left">完成质量：</div>
									<div class="w-evaluation-conter">
										<div class="w-barline w-orange-star-gray">
											<div style="width: 210px;" class="w-charts w-orange-star"></div>
										</div>
									</div>
									<div class="w-evaluation-right">
										<span class="w-evaluation_view">5.0/5</span>
									</div>
								</div>
								<div class="w-evaluation-box_1">
									<div class="w-evaluation-left">好评率：</div>
									<div class="w-evaluation-conter">
										<div class="w-barline w-red-star-gray">
											<div style="width: 210px;" class="w-charts w-red-star"></div>
										</div>
									</div>
									<div class="w-evaluation-right">
										<span class="w-evaluation_view">5/5</span>
									</div>
								</div>
								<div class="w-evaluation-box_1">
									<div class="w-evaluation-left">工作速度：</div>
									<div class="w-evaluation-conter">
										<div class="w-barline w-green-star-gray">
											<div style="width: 210px;" class="w-charts w-green-star"></div>
										</div>
									</div>
									<div class="w-evaluation-right">
										<span class="w-evaluation_view">5.0/5</span>
									</div>
								</div>
								<div class="w-evaluation-box_1">
									<div class="w-evaluation-left">交易量：</div>
									<div class="w-evaluation-conter">0</div>
									<div class="w-evaluation-right">笔</div>
								</div>
								<div class="w-evaluation-box_1">
									<div class="w-evaluation-left">服务态度：</div>
									<div class="w-evaluation-conter">
										<div class="w-barline w-red-hart-gray">
											<div style="width: 210px;" class="w-charts w-red-hart"></div>
										</div>
									</div>
									<div class="w-evaluation-right">
										<span class="w-evaluation_view">5.0/5</span>
									</div>
								</div>
								<div class="w-evaluation-box_1">
									<div class="w-evaluation-left">交易额：</div>
									<div class="w-evaluation-conter">¥0.00</div>
									<div class="w-evaluation-right">元</div>
								</div>
							</div>
						</dd>
						<dd>
							<div class="u-tab">
								<a href="javascript:void(0);" id="ids0" class="active"
									onclick="showTabs(0)">来组雇主的评价</a><a href="javascript:void(0);"
									id="ids1" onclick="showTabs(1)">来组雇员的评价</a>
							</div>
							<div class="w-tab-content">
								<div id="tabs0" style="display: block;">
									<div class="w-evalu">
										<a href="javascript:void(0)"> <input name="mark_status"
											value="1" checked="checked" onclick="set_mark_status(1)"
											type="radio"> 好评
										</a> <a href="javascript:void(0)"> <input name="mark_status"
											value="2" onclick="set_mark_status(2)" type="radio">
											中评
										</a> <a href="javascript:void(0)"> <input name="mark_status"
											value="3" onclick="set_mark_status(3)" type="radio">
											差评
										</a>


									</div>
									<div class="w-tab-content w-table-record">
										<div id="tabs00" style="display: block;">
											<table border="0" cellpadding="0" cellspacing="0"
												width="100%">
												<tbody>
													<tr>
														<th>雇主</th>
														<th>任务</th>
														<th>收入</th>
														<th>成交时间</th>
														<th>评价内容</th>
													</tr>
												</tbody>
											</table>
										</div>
										<div id="tabs01">
											<table border="0" cellpadding="0" cellspacing="0"
												width="100%">
												<tbody>
													<tr>
														<th>雇员</th>
														<th>任务</th>
														<th>收入</th>
														<th>成交时间</th>
														<th>评价内容</th>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div id="tabs1">
									<div class="w-evalu">
										<a href="javascript:void(0)"> <input name="mark_status"
											value="1" checked="checked" onclick="set_mark_status(1)"
											type="radio"> 好评
										</a> <a href="javascript:void(0)"> <input name="mark_status"
											value="2" onclick="set_mark_status(2)" type="radio">
											中评
										</a> <a href="javascript:void(0)"> <input name="mark_status"
											value="3" onclick="set_mark_status(3)" type="radio">
											差评
										</a>
									</div>
								</div>
							</div>
						</dd>
					</dl>
					<dl class="uDllist">
						<dt>交易记录</dt>
						<dd>
							<div class="u-tab">
								<a href="javascript:void(0);" id="ids00" class="active"
									onclick="showTabs0(0)">我的中标</a><a href="javascript:void(0);"
									id="ids01" onclick="showTabs0(1)">我的雇佣</a>
							</div>
							<div class="w-tab-content w-table-record">
								<div id="tabs00" style="display: block;">
									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tbody>
											<tr>
												<th>雇主</th>
												<th>任务</th>
												<th>类型</th>
												<th>收入</th>
												<th>成交时间</th>
											</tr>
										</tbody>
									</table>
								</div>
								<div id="tabs01">
									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tbody>
											<tr>
												<th>雇主</th>
												<th>任务</th>
												<th>类型</th>
												<th>收入</th>
												<th>成交时间</th>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<!--footer-->
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>


</body>
</html>

<script type="text/javascript">
	var userId = '${user.id}';

	function addFollow() {
		$.getJSON("${pageContext.request.contextPath}/follow/json/add", {
			userId : userId
		},

		function(data) {
			if (data.code == 1) {
				alert(data.msg);
			}
		});
	}
</script>