<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/content/public/commons.jspf"%>
<link rel="stylesheet" type="text/css"
	href="webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="css/upload.css" />
<script type="text/javascript" src="webuploader/webuploader.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/content/public/head.jspf"%>
	<div class="container content-box">
		<div class="row">
			<div class="col-md-2 mycenter-left-nav">
				<%@ include file="/WEB-INF/content/public/left-list.jspf"%>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">文档上传</div>
					<div class="panel-body">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<i class="glyphicon glyphicon-info-sign"></i> 上传文档
						</div>
						<div id="uploader" class="wu-example form-horizontal">
							<!--<label for="inputDocName" class="col-sm-2 control-label"></label>-->
							<div id="thelist" class="uploader-list"></div>
							<div class="btns">
								<div id="picker">选择文件</div>
								<button id="ctlBtn" class="btn btn-default">开始上传</button>
							</div>
						</div>
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<i class="glyphicon glyphicon-info-sign"></i> 填写文档基本信息
						</div>
						<form class="form-horizontal" id="doc-upload-form" action="doc/add-doc-info.action" method="post">
							<input type="hidden" value="${doc.docId }" name="docId"
								id="docId"><br>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">名称</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputDocName"
										name="docName" placeholder="请输入文档名称">
								</div>
								<div class="col-sm-2 input-warning">请填写文档名称</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">类型</label>
								<div class="col-sm-4">
									<select class="form-control">
										<option value="none">请选择</option>
										<option>技术类</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select class="form-control" name="typeId">
										<option value="18">请选择</option>
										<option value="18">Java</option>
									</select>
								</div>
								<div class="col-sm-2">请选择类型</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">权限</label>
								<div class="col-sm-8">
									<select class="form-control" name="docAuthority">
										<option value="1">公开</option>
										<option value="2">会员</option>
										<option value="3">仅自己</option>
									</select>
								</div>
								<div class="col-sm-2">请选择文档查看权限</div>
							</div>

							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">文档说明</label>
								<div class="col-sm-8">
									<textarea class="form-control" id="doc-desc" rows="3" name="docDesc"
										placeholder="请对文档进行简要的说明"></textarea>
								</div>
								<div class="col-sm-2">请输入文档说明</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">提交</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script>
	var $ = jQuery, $list = $('#thelist'), $btn = $('#ctlBtn'), state = 'pending', uploader;
	var nowFile = null;
	uploader = WebUploader.create({
		// 不压缩image
		resize : false,
		// swf文件路径
		swf : '/webuploader/Uploader.swf',
		// 文件接收服务端。
		server : 'doc/file-upload',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#picker',
		formData : {
			fileName : "12.doc"
		}
	});
	// 当有文件添加进来的时候
	uploader.on('fileQueued', function(file) {
		if (nowFile != null) {
			uploader.removeFile(nowFile);
		}
		nowFile = file;

		$("#inputDocName").val(
				file.name.substring(0, file.name.lastIndexOf(".")));

		$list.html('<div id="' + file.id + '" class="item">'
				+ '<h4 class="info">' + file.name + '</h4>'
				+ '<p class="state">等待上传...</p>' + '</div>');
	});

	//startUpload
	uploader.on('uploadStart', function(file) {

	});

	// 文件上传过程中创建进度条实时显示。
	uploader
			.on(
					'uploadProgress',
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress .progress-bar');
						// 避免重复创建
						if (!$percent.length) {
							$percent = $(
									'<div class="progress progress-striped active">'
											+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
											+ '</div>' + '</div>')
									.appendTo($li).find('.progress-bar');
						}
						$li.find('p.state').text('上传中');
						$percent.css('width', percentage * 100 + '%');
					});
	uploader.on('uploadSuccess', function(file) {
		$('#' + file.id).find('p.state').text('已上传');
		$("#picker").remove();
		$("#ctlBtn").remove();

	});
	uploader.on('uploadError', function(file) {
		$('#' + file.id).find('p.state').text('上传出错');
	});
	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
	});
	uploader.on('all', function(type) {
		if (type === 'startUpload') {
			state = 'uploading';
		} else if (type === 'stopUpload') {
			state = 'paused';
		} else if (type === 'uploadFinished') {
			state = 'done';
		}
		if (state === 'uploading') {
			$btn.text('暂停上传');
		} else {
			$btn.text('开始上传');
		}
	});
	$btn.on('click', function() {
		if (state === 'uploading') {
			uploader.stop();
		} else {
			var file = uploader.getFiles()[0];
			if ("" == $("#docId").val().trim()) {
				$.post("doc/add-get-doc", function(data) {
					$("#docId").val(data.docId);
					uploader.option('formData', {
						fileName : file.name,
						contentType : file.type,
						docId : $("#docId").val()
					});
					uploader.upload();
				});
			} else {
				uploader.option('formData', {
					fileName : file.name,
					contentType : file.type,
					docId : $("#docId").val()
				});
				uploader.upload();
			}

		}
	});
	$("#doc-upload-form").submit(function() {
		if ($("#inputDocName").val() == "") {
			$("#inputDocName").parent().parent().addClass("has-error");
		} else {
			$("#inputDocName").parent().parent().removeClass("has-error")
		}
		if ($("#doc-desc").val() == "") {
			$("#doc-desc").parent().parent().addClass("has-error");
		} else {
			$("#doc-desc").parent().parent().removeClass("has-error")
		}
		if ($(this).find(".has-error").length == 0) {
			return true;
		} 
			
		return false;
		
	});
</script>
</html>
