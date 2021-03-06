<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>潮人码头 - 文化产业电子商务平台 - 登录</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right" style=" height:600px;">
			<div class="col-xs-12 col-sm-8">
				<div class="vipLogin m_r20">
					<h2 class="l-title">会员登录</h2>
					<div class="col-sm-12 col-md-6">

						<form name="form1" id="form1"
							action="${pageContext.request.contextPath}/user/action/login"
							method="POST">
							<input name="redirect" value="${redirect}" type="hidden" />
							<div class="form-table">

								<label>用户名</label> <input id="username" name="username" type="text"
									class="form-control">
							</div>
							<div class="form-table">
								<label>密码</label> <input id="password" name="password" type="password"
									class="form-control">
							</div>
							<a href="javascript:void(0)" onclick="checkSubmit()"
								class="redBtn5">登录</a>
						</form>

					</div>

					<div class="col-sm-12 col-md-6" style="display:none">

						<div class="shouquan">
							<p>
								<a href="#"><img
									src="${pageContext.request.contextPath}/resource/web/images//wblogin.png"
									width="146" height="32"></a>
							</p>
							<p>
								<a href="#"><img
									src="${pageContext.request.contextPath}/resource/web/images//qqlogin.png"
									width="170" height="32"></a>
							</p>
							<p>
								<a href="javascript:void(0)"><img
									src="${pageContext.request.contextPath}/resource/web/images//dblogin.png"
									width="168" height="32"></a>
							</p>
						</div>


					</div>
				</div>
				<div class="col-sm-12 m_t30" style="display:none">
					<div class="phoneLogin m_r20">
						<h2 class="l-title">手机登录</h2>
						<div class="col-sm-12 col-md-6">

							<form name="form2"
								action="${pageContext.request.contextPath}/member/login"
								method="POST">
								<div class="input-table p_t10">
									<input class="form-control" placeholder="请输入你的手机号">
								</div>
								<a href="#" class="redBtn5 m_t30">登录</a>
								<div class="notice">
									<p>* 请输入你首次创建应用时候的手机号.</p>
									<p>* 请确保你输入的手机号能接收验证码.</p>
									<p>* 首次使用手机登录网站不需要验证码.</p>
								</div>

							</form>
						</div>

					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="help m_l20">
					<h2 class="l-title">需要帮助？</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/webpage/reg">新用户注册</a></li>
				 
						<li style="display:none"><a href="#">忘记密码？</a></li>
						<li style="display:none"><a href="#">查看帮助说明</a></li>
						
						<li>
							<dl>
								<dt>联系客服：</dt>
								<dd class="p_t10">
									<img
										src="${pageContext.request.contextPath}/resource/web/images/icon-qq-boy.png"
										width="60" height="60"><img
										src="${pageContext.request.contextPath}/resource/web/images/icon-qq-girl.png"
										width="60" height="60"><img
										src="${pageContext.request.contextPath}/resource/web/images/icon-weixin.png"
										width="60" height="60">
								</dd>
								<dd class="p_t25">
									热线电话：<span class="f_c_fd0100">18200000000</span>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>
${msg}

<script type="text/javascript">
function checkSubmit()
{
	if($('#username').val()==''||$('#password').val()=='')
	{
		alert('请输入用户名密码.');
		return;
	}
	
	
	form1.submit();
	
}


$(document).keyup(function(event){
	  if(event.keyCode ==13){
		  checkSubmit();
	  }
});

</script>