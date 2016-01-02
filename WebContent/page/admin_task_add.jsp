<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增任务</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<c:import url="/user/usernav" />

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_task.jsp"></jsp:include>
			</div>
			<div class="col-md-10">




				<div class="row">
					<div class="col-lg-12">
						<c:if test="${msg!=null}">
							<div class="alert alert-info">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								${msg}
							</div>

						</c:if>
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">发布任务</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/task/action/add"
									name="form1" method="post" class="form-horizontal">
									<input name="topType" value="${topType }" type="hidden" />
									<fieldset>
										<legend>基本信息</legend>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">任务标题</label>
											<div class="col-lg-10">
												<input name="title" type="text"
													class="form-control col-md-6" id="title" autocomplete="off"
													data-provide="typeahead" data-items="4" data-source="">
												<p class="help-block">请用一句话来描述您的任务最多20个字</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">任务分类</label>
											<div class="col-lg-10">
												<select name="parentTypeId" id="parentTypeId"
													class="form-control">

												</select> <span class="help-block"></span>
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError"></label>
											<div class="col-lg-10">
												<select name="subTypeId" id="subTypeId" class="form-control">


												</select> <span class="help-block"></span>
											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">任务内容</label>
											<div class="col-lg-10">

												<textarea id="ckeditor_full" name="content"
													class="form-control textarea-wysihtml5"
													placeholder="输入正文..."></textarea>

											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">封面图</label>
											<div class="col-lg-10">
												<img id="upimage" src="" alt="" width="120" height="90" />
												<input name="thumb" id="thumb" value="" type="hidden" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传图片</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upload"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传附件</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upfile"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">Q
												Q</label>
											<div class="col-lg-10">
												<input type="text" name="QQ" class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">手机</label>
											<div class="col-lg-10">
												<input name="phone" type="text"
													class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">联系人</label>
											<div class="col-lg-10">
												<input name="name" type="text" class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">称呼</label>


											<div class="col-lg-10">
												<label class="uniform"> <input class="uniform_on"
													type="radio" name="sex" value="先生" checked="checked" />先生
												</label> <label class="uniform"> <input class="uniform_on"
													type="radio" name="sex" value="女士" />女士

												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">邮箱</label>
											<div class="col-lg-10">
												<input name="email" type="text"
													class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">座机</label>
											<div class="col-lg-10">
												<input name="tel" type="text" class="form-control col-md-6" />

											</div>
										</div>
										<legend>悬赏模式</legend>

										<div id="rootwizard">
											<div class="navbar">
												<div class="container">
													<ul class="nav nav-pills">
														<li onclick="bountyMode.value=1" class="active"><a
															href="#tab1" data-toggle="tab">单人悬赏 </a></li>
														<li onclick="bountyMode.value=2" class=""><a
															href="#tab2" data-toggle="tab">计件悬赏 </a></li>
														<input name="bountyMode" id="bountyMode" type="hidden"
															value="1">
													</ul>
												</div>
											</div>

											<div class="tab-content">
												<div class="form-group">
													<label class="col-lg-2 control-label" for="focusedInput1">悬赏单价</label>
													<div class="col-lg-10">
														<input name="bountyPrice" class="form-control"
															id="bountyPrice" type="text" value="0">
													</div>
												</div>
												<div class="tab-pane active" id="tab1"></div>
												<div class="tab-pane" id="tab2">

													<div class="form-group">
														<label class="col-lg-2 control-label" for="focusedInput5">选稿数量</label>
														<div class="col-lg-10">
															<input name="bountyCount" class="form-control"
																id="bountyCount" type="text" value="1">
														</div>
													</div>
												</div>
											</div>
										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label">投稿的时间</label>
											<div class="col-lg-10">
												<input type="text" name="endDateStr"
													class="form-control datepicker" id="date01"
													value="2015-01-01">
												<p class="help-block">投稿截止后，您有48小时的时间进行选稿。</p>
											</div>
										</div>


										<legend>发布结算</legend>
										<div class="form-group">
											<label class="col-lg-2 control-label">您发布的任务</label>
											<div class="col-lg-10">

												<p id="taskTitle" class="help-block">范例：XXX珠宝公司LOGO设计</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">您设定的悬赏模式</label>
											<div class="col-lg-10">

												<p id="pType" class="help-block">单人悬赏</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label">您设定的悬赏金额</label>
											<div class="col-lg-10">

												<p id="pPrice" class="help-block">0元</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label">您设定的截稿时间</label>
											<div class="col-lg-10">

												<p class="help-block" id="endate">2015-1-1</p>
												<p class="help-block">在投稿截止后，您有48小时的时间进行选稿</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label">结算清单</label>
											<div class="col-lg-10">

												<p class="help-block">托管赏金：<span id="pboundy" style="color:#f00"></span>元</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label">应付总额</label>
											<div class="col-lg-10">

												<p class="help-block"><span id="payTotal"></span>元</p>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">支付方式</label>
											<div class="col-lg-10">

												线下支付(请前往潮人码头项目管理办公室支付)
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label"></label>
											<div class="col-lg-10">

												<input name="accept" checked="checked" type="checkbox" />同意《潮人码头任务发布协议》
											</div>
										</div>
										
										<button type="submit" class="btn btn-primary">提交</button>
										<button type="reset" class="btn btn-default">取消</button>

									</fieldset>


								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



</body>

</html>
${js }

<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	var editor;

	$(function() {
		$('#date01').val(getToday());

		$('.datepicker').datepicker({
			format : 'yyyy-mm-dd',
			weekStart : 1,
			autoclose : true,
			todayBtn : 'linked'
		});
		// CKEditor Full
		editor = $('textarea#ckeditor_full').ckeditor({
			height : '320px'
		}).editor;

		getParentType();

	})

	$('#parentTypeId').bind('change', function() {

		selectClear('#subTypeId');
		getSubType($('#parentTypeId').val());

	});

	function getParentType() {
		$.getJSON("${pageContext.request.contextPath}/tasktype/json/list", {
			parentId : ""
		}, function(data) {
			$.each(data, function(i, item) {
				if (i == 0) {
					getSubType(item.id);
				}
				selectAdd("#parentTypeId", item.id, item.name);
			});
		});
	}

	function getSubType(pid) {
		selectClear("#subTypeId");
		$.getJSON("${pageContext.request.contextPath}/tasktype/json/list", {
			parentId : pid,
			t : new Date()
		}, function(data) {
			$.each(data, function(i, item) {
				selectAdd("#subTypeId", item.id, item.name);
			});
		});
	}

	function selectAdd(name, value, text) {
		var selObj = $(name);
		selObj.append("<option value='"+value+"'>" + text + "</option>");
	}

	// 清空
	function selectClear(name) {
		var selOpt = $(name + " option");
		selOpt.remove();
	}

	function upfile(jsonstr) {
		var obj = jQuery.parseJSON(jsonstr);
		

		if (obj.cover == 1) {
			$('#thumb').val(obj.path + getThumb(obj.name, 240, 180));
			$('#upimage').attr('src', obj.path + getThumb(obj.name, 240, 180));
			

		}else
		{
			editor.insertHtml('<img src="' + obj.path
					+ getThumb(obj.name, 1000, 1000) + '"/>');
		}

	}

	function getThumb(filename, w, h) {
		var ext = filename.substring(filename.lastIndexOf('.'));
		var newfile = filename.substring(0, filename.lastIndexOf('.')) + "_"
				+ w + "x" + h + ext;
		return newfile;
	}
	
	
	function countForm()
	{
		$('#taskTitle').text($('#title').val());
		if($('#bountyMode').val()=="1")
		{
			$('#pType').text('单人悬赏');
			$('#pboundy').text($('#bountyPrice').val());
			$('#pPrice').text($('#bountyPrice').val());
			$('#payTotal').text($('#bountyPrice').val());
		}
		if($('#bountyMode').val()=="2")
		{
			$('#pType').text('计件悬赏');
			$('#pPrice').text('单价:'+$('#bountyPrice').val()+'元, 稿件:'+$('#bountyCount').val()+'件');
			$('#pboundy').text($('#bountyPrice').val()*$('#bountyCount').val());
			$('#payTotal').text($('#bountyPrice').val()*$('#bountyCount').val());
		}
		
		$('#endate').html($('#date01').val());
	}
	
	function upfile2(jsonstr) {
		var obj = jQuery.parseJSON(jsonstr);
		editor
				.insertHtml('<a target="_blank" href="' + obj.path
				+ obj.name + '">下载附件<a/>');

	}
	
	function getToday()
	{
		var d = new Date();
		var year = d.getFullYear();
		var month = d.getMonth() + 1; // 记得当前月是要+1的
		month = month < 10 ? ("0" + month) : month;
		var dt = d.getDate();
		dt = dt < 10 ? ("0" + dt) : dt;
		var today = year + "-" + month + "-" + dt;
		
		return today;
	}
	
	setInterval(countForm, 1000);
</script>

