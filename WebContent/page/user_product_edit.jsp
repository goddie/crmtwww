<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑商品</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<c:import url="/user/usernav" />

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_product.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">发布商品</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/product/action/edit"
									name="form1" method="post" class="form-horizontal">
									<input type="hidden" name="id" value="${entity.id }"/>
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">商品名称</label>
											<div class="col-lg-10">
												<input name="name" type="text" class="form-control col-md-6"
													id="name" value="${entity.name }">

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">商品标签</label>
											<div class="col-lg-10">
												<input name="tag" type="text" class="form-control col-md-6"
													id="tag" value="${entity.tag }">
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">商品类别</label>


											<div class="col-lg-10">
												<label class="uniform"> <input class="uniform_on"
													type="radio" name="topType" value="1" checked="checked" />商品
												</label> <label class="uniform"> <input class="uniform_on"
													type="radio" name="topType" value="2" />服务

												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">商品分类</label>
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
											<label class="col-lg-2 control-label" for="typeahead">商品售价</label>
											<div class="col-lg-10">
												<input name="price" type="text"
													class="form-control col-md-6" id="price" value="${entity.price }">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">商品描述</label>
											<div class="col-lg-10">

												<textarea id="ckeditor_full" name="info"
													class="form-control textarea-wysihtml5">${entity.info }</textarea>

											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">封面图</label>
											<div class="col-lg-10">
												<img id="upimage" src="${entity.thumb }" alt="" width="120" height="90" />
												<input name="thumb" id="thumb" value="${entity.thumb }" type="hidden" />
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
										<input name="attachment" id="attachment" value=""
											type="hidden" />
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传商品</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upfile"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">手机号码</label>
											<div class="col-lg-10">
												<input name="phone" type="text"
													class="form-control col-md-6" id="phone" value="${entity.phone }">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">QQ号码</label>
											<div class="col-lg-10">
												<input name="QQ" id="QQ" type="text"
													class="form-control col-md-6" value="${entity.QQ }">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">原创证明</label>
											<div class="col-lg-10">
												<img id="imgproof" name="imgproof" src="${entity.proof }" alt="" width="120"
													height="90" /> <input name="proof" id="proof"
													value="${entity.proof }" type="hidden" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传原创证明</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upload2?fieldName=proof"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收款帐号</label>
											<div class="col-lg-10">
												<input name="bankNo" id="bankNo" type="text"
													class="form-control col-md-6" value="${entity.bankNo }">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">再一遍收款帐号</label>
											<div class="col-lg-10">
												<input name="bankNo2" id="bankNo2" type="text"
													class="form-control col-md-6" value="${entity.bankNo }">
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收款户名</label>
											<div class="col-lg-10">
												<input name="bankAccount" id="bankAccount" type="text"
													class="form-control col-md-6" value="${entity.bankAccount }"/>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收款银行</label>
											<div class="col-lg-10">
												<input name="bankName" id="bankName" type="text"
													class="form-control col-md-6" value="${entity.bankName }" />
											</div>
										</div>

										<div class="form-group">

											<label class="col-lg-2 control-label" for="typeahead"></label>
											<div class="col-lg-10">
												<input type="checkbox" checked="checked" />同意<a
													href="${pageContext.request.contextPath}/article/detail?id=6fe81e11-e67c-4639-9ed3-5d454dc73508"
													target="_blank">《潮人码头自媒体内容在线协同工作平台版权声明》</a>
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

	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>


	<script type="text/javascript">
		var editor;
		
		var pid = '${entity.parentType.id}';
		var pname = '${entity.parentType.name}';
		var sid = '${entity.subType.id}';
		var sname = '${entity.subType.name}';

		$(function() {

			// CKEditor Full
			editor = $('textarea#ckeditor_full').ckeditor({
				height : '220px'
			}).editor;

			getParentType();

		})

		$('#parentTypeId').bind('change', function() {

			getSubType($('#parentTypeId').val());

		});

		function getParentType() {
			$.getJSON("${pageContext.request.contextPath}/tasktype/json/list",
					{
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
			$.getJSON("${pageContext.request.contextPath}/tasktype/json/list",
					{
						parentId : pid
					}, function(data) {
						$.each(data, function(i, item) {
							selectAdd("#subTypeId", item.id, item.name);
						});
					});
			
			selectAdd('#parentTypeId',pid,pname);
			selectAdd('#subTypeId',sid,sname);
			$("#parentTypeId option[value='"+pid+"']").attr("selected", "selected"); 
			$("#subTypeId option[value='"+sid+"']").attr("selected", "selected");
			
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
				$('#upimage').attr('src',
						obj.path + getThumb(obj.name, 240, 180));
				$('#thumb').val(obj.path + getThumb(obj.name, 240, 180));
			}

		}

		function getThumb(filename, w, h) {
			var ext = filename.substring(filename.lastIndexOf('.'));
			var newfile = filename.substring(0, filename.lastIndexOf('.'))
					+ "_" + w + "x" + h + ext;
			return newfile;
		}

		function upfile2(jsonstr) {
			var obj = jQuery.parseJSON(jsonstr);

			editor
					.insertHtml('<div class="attachment"><a target="_blank" href="' + obj.path
					+ obj.name + '">商品文件<a/></div>');

		}

		function uploadImage2(jsonstr) {
			var obj = jQuery.parseJSON(jsonstr);

			$('#img' + obj.field).attr('src',
					obj.path + getThumb(obj.name, 240, 180));
			$('#' + obj.field).val(obj.path + getThumb(obj.name, 240, 180));
		}
	</script>

</body>

</html>

${js}
