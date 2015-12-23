<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>潮人码头 - 文化产业电子商务平台 - 社区</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<c:import url="/webpage/webnav" />
	<div class="container">
		<div class="row">

			<div class="forum-block clearfix">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<h3>发布新帖</h3>
					<div class="content-body">
						<form
							action="${pageContext.request.contextPath}/forum/action/add"
							name="form1" method="post" class="form-horizontal">
							<input name="topType" value="${topType }" type="hidden" />
							<fieldset>
								<div class="form-group">
									<label class="col-lg-2 control-label">分类</label>
									<div class="col-lg-8">
										<select name="parentTypeId" id="parentTypeId"
											class="form-control">

										</select> <span class="help-block"></span>
									</div>

								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label" for="selectError"></label>
									<div class="col-lg-8">
										<select name="subTypeId" id="subTypeId" class="form-control">
										</select> <span class="help-block"></span>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label" for="typeahead">标题</label>
									<div class="col-lg-8">
										<input name="title" type="text" class="form-control col-md-6"
											id="title" autocomplete="off" data-provide="typeahead"
											data-items="4" data-source="">
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label" for="typeahead">封面图</label>
									<div class="col-lg-8">
										<img id="upimage" src="" alt="" width="120" height="90" /> <input
											name="thumb" id="thumb" value="" type="hidden" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label" for="fileInput">上传图片</label>
									<div class="col-lg-8">
										<iframe
											src="${pageContext.request.contextPath}/album/page/upload"
											style="height: 34px; width: 100%;" frameborder="0"
											marginheight="0" marginwidth="0" scrolling="no"></iframe>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label" for="textarea-wysihtml5">内容</label>
									<div class="col-lg-8">

										<textarea id="ckeditor_full" name="content"
											class="form-control textarea-wysihtml5" placeholder="输入正文..."></textarea>

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
	<!--footer-->
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>
${msg }

<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>
 
<script type="text/javascript">
	var editor;

	$(function() {

	 
		// CKEditor Full
		editor = $('textarea#ckeditor_full').ckeditor({
			height : '320px'
		}).editor;

		getParentType();

	})

	$('#parentTypeId').bind('change', function() {

		getSubType($('#parentTypeId').val());

	});

	function getParentType() {
		$.getJSON("${pageContext.request.contextPath}/forumtype/json/list", {
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
		$.getJSON("${pageContext.request.contextPath}/forumtype/json/list", {
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
		editor.insertHtml('<img src="' + obj.path
				+ getThumb(obj.name, 1000, 1000) + '"/>');

		if (obj.cover == 1) {
			$('#upimage').attr('src', obj.path + getThumb(obj.name, 240, 180))
			$('#thumb').val(obj.path + getThumb(obj.name, 240, 180));
		}

	}

	function getThumb(filename, w, h) {
		var ext = filename.substring(filename.lastIndexOf('.'));
		var newfile = filename.substring(0, filename.lastIndexOf('.')) + "_"
				+ w + "x" + h + ext;
		return newfile;
	}
</script>