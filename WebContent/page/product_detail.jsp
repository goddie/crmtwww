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
<title>潮人码头 - 文化产业电子商务平台 - 创意商城</title>

<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>


<script type="text/javascript">
	function showTab_c(num) {
		for (i = 0; i < 3; i++) {
			document.getElementById("tabs" + i).style.display = "none";
			document.getElementById("ids" + i).className = "noborder";

		}
		document.getElementById("tabs" + num).style.display = "block";
		document.getElementById("ids" + num).className = "orangeborder";
	}
</script>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="graybg">
		<div class="container">
			<div class="col-xs-12 col-sm-9">
				<p>首页 > 创意商城 > 商品详情</p>
				<h1 class="d-title">${entity.name}</h1>
				<p class="p_t10">
					<img src="${entity.user.head }" width="27" height="27">&nbsp;&nbsp;${entity.user.nickname}
				</p>
				<p class="p_t25">商品类别：${entity.parentType.name}
					${entity.subType.name}&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</div>
			<div class="col-xs-6 col-sm-3">
				<p class="f24 fwb">${entity.visitCount }</p>
				<p class="p_l6">浏览人数</p>
				<p class="p_t10">
					<a
						href="${pageContext.request.contextPath}/product/v/addorder?productId=${entity.id }"
						target="_blank" class="redBtn1">购买</a><b>购买人数
						${entity.tradeCount }</b>
				</p>
				<p>
					<a href="javascript:void(0)" onclick="collect('${entity.id}')"
						class="grayBtn1">收藏</a><b>收藏人数 ${entity.collectCount }</b>
				</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<div class="picScroll" style="display: none;">
					<div class="sliderbox">
						<div class="bigpic">
							<div class="btn pre"></div>
							<div class="btn next"></div>
							<img class="bpic" src="images/pic6.jpg" />

						</div>
						<div id="ztbox">
							<div class="spic">
								<div id="left"></div>
								<div id="conter">
									<ul>
										<li class="on"><img class="smallpic"
											src="images/pic6.jpg" /></li>
										<li><img class="smallpic" src="images/pic7.jpg" /></li>
										<li><img class="smallpic" src="images/pic23.jpg" /></li>
										<li><img class="smallpic" src="images/pic24.jpg" /></li>
										<li><img class="smallpic" src="images/pic25.jpg" /></li>
									</ul>
								</div>
								<div id="right"></div>
							</div>
							<div id="scroll">
								<span></span>
							</div>
						</div>
					</div>
				</div>
				<h2>售价</h2>
				<p style="color:#f00; font-size:2em;">${entity.price}</p>
				<h2>描述</h2>
				<p>${entity.info}</p>
				<a
					href="${pageContext.request.contextPath}/product/v/addorder?productId=${entity.id }"
					target="_blank" class="redBtn2">购买</a>
				<div class="clearfix">
					<h2>留言</h2>
					<textarea name="content" id="content" cols="" rows="" class="txt"
						placeholder=""></textarea>
					<a href="javascript:void(0)" onclick="addFeedback()"
						class="redBtn4 fr">发表评论</a>
				</div>
				<div class="p_t25">
					<div class="tab">
						<a id="ids0" onclick="showTab_c(0)" class="orangeborder">留言</a><span
							style="display: none;">|<a id="ids1"
							onclick="showTab_c(1)" class="noborder">购买记录</a>|<a id="ids2"
							onclick="showTab_c(2)" class="noborder">评论</a></span>
					</div>
					<div class="tab_content" id="tabs0" style="display: block;">
						<ul class="detaillist">
							<c:import url="/feedback/productlist">
								<c:param name="productId" value="${entity.id }" />
								<c:param name="p" value="1" />
							</c:import>
						</ul>
					</div>
					<div class="tab_content" id="tabs1">
						<div
							style="padding: 100px 0px; text-align: center; margin: 0 auto;">暂无购买记录</div>
					</div>
					<div class="tab_content" id="tabs2">
						<div
							style="padding: 100px 0px; text-align: center; margin: 0 auto;">暂无评论记录</div>
					</div>
				</div>
			</div>

		</div>


		<hr>
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>
${js }
<script type="text/javascript">
	function buy(pid) {
		$.getJSON("${pageContext.request.contextPath}/producttrade/json/buy", {
			pid : pid
		},

		function(data) {
			if (data.code == 1) {
				alert(data.msg);
			}
		});
	}

	function collect(pid) {
		$.getJSON("${pageContext.request.contextPath}/collect/json/add", {
			productId : pid
		},

		function(data) {
			if (data.code == 1) {
				alert(data.msg);
			}
		});
	}

	function addFeedback() {
		$.getJSON("${pageContext.request.contextPath}/feedback/json/add", {
			parentId : "",
			productId : "${entity.id}",
			content : $("#content").val(),
			t : new Date()
		}, function(obj) {

			if (obj.code != 1) {
				alert(obj.msg);
			} else {
				window.location.reload();
			}

		});
	}
</script>