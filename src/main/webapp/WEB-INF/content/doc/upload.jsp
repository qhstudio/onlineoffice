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
							(提示：文件必须小于1000KB)
						</div>
						<div id="uploader" class="wu-example form-horizontal">
							<!--<label for="inputDocName" class="col-sm-2 control-label"></label>-->
							<div id="thelist" class="uploader-list">
								<c:if test="${upDoc != null }">
									<div id="" class="item">
										<h4 class="info">${upDoc.docName}.${upDoc.docFoot}</h4>
										<p class="state">当前文档</p>
									</div>
								</c:if>
							</div>
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
						<form class="form-horizontal" id="doc-upload-form"
							action="doc/add-doc-info.action" method="post">
							<input type="hidden" value="${upDoc.docId }" name="docId"
								id="docId"><br>
									<input type="hidden" value="${pageNum}" name="pageNum"
								id="docId">
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">名称</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputDocName"
										name="docName" placeholder="请输入文档名称" value="${upDoc.docName }">
								</div>
								<div class="col-sm-2 input-warning">请填写文档名称</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">类型</label>
								<div class="col-sm-4">
									<select class="form-control" id="parent-type">
									</select>
								</div>
								<div class="col-sm-4">
									<select class="form-control" name="typeId" id="childType">
									</select>
									<script type="text/javascript">
										
									</script>
								</div>
								<div class="col-sm-2">请选择类型</div>
							</div>
							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">权限</label>
								<div class="col-sm-8">
									<select class="form-control" name="docAuthority"
										id="docAuthority">
										<option value="1">公开</option>
										<option value="2">会员</option>
										<option value="3">仅自己</option>
									</select>
									<script type="text/javascript">
										if ($("#docId").val() != null
												&& $("#docId").val().trim() != '') {
											$("#docAuthority").val(
													'${upDoc.docAuthority}');
										}
									</script>
								</div>
								<div class="col-sm-2">请选择文档查看权限</div>
							</div>

							<div class="form-group">
								<label for="inputDocName" class="col-sm-2 control-label">文档说明</label>
								<div class="col-sm-8">
									<textarea class="form-control" id="doc-desc" rows="3"
										name="docDesc" placeholder="请对文档进行简要的说明">${upDoc.docDesc }</textarea>
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
	$("#left-list-nav").find("a").eq(2).addClass("active");

	var isUpload = false;
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
		},
		fileSizeLimit : 1020800,
		accept : {
			extensions : 'doc,docx,ppt,pptx,xls,xlsx'
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
	uploader.on('error', function(type) {
		if (type == 'Q_EXCEED_SIZE_LIMIT') {
			alert('文件大小太大');
		} else if (type == 'Q_TYPE_DENIED') {
			alert('文件类型不支持');
		}

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
		isUpload = true;
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
			if ($("#docId").val() == null || $("#docId").val().trim() == '') {
				alert("请先上传文件!");
				return false;
			}
			return true;
		}

		return false;

	});

	//获得分类
	//json/json-doc-types
	var type_data;
	$.post("json/json-doc-types", function(data) {
		type_data = data;
		
		
		
		for (var i = 0; i < data.doctypes.length; i++) {
			var str = "<option value="+data.doctypes[i].typeId+">"+data.doctypes[i].typeName+"</option>";
			$("#parent-type").append(str);
			if(i == 0){
				$("#childType").append(str);
			}
			
		}
		
		
		for (var j = 0; j < type_data.doctypes[0].childrenDocType.length; j++) {
		 	var str = "<option value="+type_data.doctypes[0].childrenDocType[j].typeId+">"+type_data.doctypes[0].childrenDocType[j].typeName+"</option>";
			$("#childType").append(str);
		}
		
		$("#parent-type").change(function(){
			$("#childType").html("");
			
			var key = $(this).val();
			for (var i = 0; i < type_data.doctypes.length; i++) {
				if(key == type_data.doctypes[i].typeId){
					var str1 = "<option value="+type_data.doctypes[i].typeId+">"+type_data.doctypes[i].typeName+"</option>";
					$("#childType").append(str1);
					for (var j = 0; j < type_data.doctypes[i].childrenDocType.length; j++) {
						 	var str = "<option value="+type_data.doctypes[i].childrenDocType[j].typeId+">"+type_data.doctypes[i].childrenDocType[j].typeName+"</option>";
							$("#childType").append(str);
					}
					break;
				}
			}
			
		});
		
		if ($("#docId").val() != null && $("#docId").val().trim() != '') {
			
				$("#parent-type").val('${upDoc.docType.parentType.typeId}');
				
				
				
				if($("#parent-type").val() == null || $("#parent-type").val().trim() == ''){
					$("#parent-type").val('${upDoc.docType.typeId}');
				}
				
				$("#childType").html("");
				var key = $("#parent-type").val();
				for (var i = 0; i < type_data.doctypes.length; i++) {
					if(key == type_data.doctypes[i].typeId){
						var str1 = "<option value="+type_data.doctypes[i].typeId+">"+type_data.doctypes[i].typeName+"</option>";
						$("#childType").append(str1);
						for (var j = 0; j < type_data.doctypes[i].childrenDocType.length; j++) {
							 	var str = "<option value="+type_data.doctypes[i].childrenDocType[j].typeId+">"+type_data.doctypes[i].childrenDocType[j].typeName+"</option>";
								$("#childType").append(str);
						}
						break;
					}
				}
				
				$("#childType").val(
						'${upDoc.docType.typeId}');
		}else{
			
		}
		
	});
	
	
	
	
	
	
</script>
</html>
