<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>账户信息</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>会员中心首页</a></li>
			<li><a href="${pageContext.request.contextPath}/user/v/userinfo"><i
					class="glyphicon glyphicon-chevron-right"></i>个人资料修改</a></li>
			<li><a href="${pageContext.request.contextPath}/user/v/avatar"><i
					class="glyphicon glyphicon-chevron-right"></i>编辑头像</a></li>
			<li><a
				href="${pageContext.request.contextPath}/user/action/logout"><i
					class="glyphicon glyphicon-chevron-right"></i>退出登录</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>教育及工作信息</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>第三方账号</a></li>
		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>安全中心</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/user/v/password"><i
					class="glyphicon glyphicon-chevron-right"></i>登录密码</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}/user/v/paypassword"><i
					class="glyphicon glyphicon-chevron-right"></i>支付密码</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>手机绑定</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>邮箱绑定</a></li>
			<li><a
				href="${pageContext.request.contextPath}/company/v/checkcompany"><i
					class="glyphicon glyphicon-chevron-right"></i>企业认证</a></li>
			<li><a
				href="${pageContext.request.contextPath}/user/v/checkperson"><i
					class="glyphicon glyphicon-chevron-right"></i>实名认证</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>校园认证</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>银行卡认证</a></li>
		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>我的财务</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/payrecord/v/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>交易记录</a></li>
			<li style="display: none"><a href="${pageContext.request.contextPath}/recharge/v/add"><i
					class="glyphicon glyphicon-chevron-right"></i>充值</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}/recharge/v/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>充值记录</a></li>
			<li style="display: none"><a href="${pageContext.request.contextPath}/cash/v/add"><i
					class="glyphicon glyphicon-chevron-right"></i>提现</a></li>
			<li style="display: none"><a href="${pageContext.request.contextPath}/cash/v/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>提现记录</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>优惠卡管理</a></li>
		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>我的消息</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a
				href="${pageContext.request.contextPath}/message/v/inbox?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>收件箱</a></li>
			<li><a
				href="${pageContext.request.contextPath}/message/v/outbox?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>发件箱</a></li>
			<li><a href="${pageContext.request.contextPath}/message/v/add"><i
					class="glyphicon glyphicon-chevron-right"></i>发送消息</a></li>
			<li style="display: none"><a
				href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>消息设置</a></li>


		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>我的主页</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/user/usersite"
				target="_blank"><i class="glyphicon glyphicon-chevron-right"></i>主页资料设置</a></li>
			<li><a href="${pageContext.request.contextPath}/work/v/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>我的作品</a></li>
			<li><a href="${pageContext.request.contextPath}/work/v/add"><i
					class="glyphicon glyphicon-chevron-right"></i>上传作品</a></li>
		</ul></li>
</ul>