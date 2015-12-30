<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/content/public/commons.jspf"%>
<link rel="stylesheet" type="text/css" href="css/docResult.css" />
<link href="umeditor1_2_2-utf8-jsp/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="umeditor1_2_2-utf8-jsp/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="umeditor1_2_2-utf8-jsp/umeditor.min.js"></script>
<script type="text/javascript"
	src="umeditor1_2_2-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="js/flexpaper.js"></script>
<script type="text/javascript" src="js/flexpaper_handlers.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/content/public/head.jspf"%>

	<div class="container content-box">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-body doc-context">
						<div id="documentViewer"
							style="width: 100%; height: 100%; padding-bottom: 30px; box-sizing: border-box;"></div>
					</div>
				</div>
				<div class="panel panel-default comment-box">

					<ul class="list-group">
						<li class="list-group-item"><b>评论</b></li>

						<li class="list-group-item" id="getMore"><button
								id="get-more-button" onclick="getMoreInfo()">查看更多</button></li>
						<li class="list-group-item comment-text">
							<!--style给定宽度可以影响编辑器的最终宽度--> <script type="text/plain"
								id="myEditor" style="width: 100%; height: 200px;">
				
								</script>

						</li>

					</ul>
					<p>
						<button class="btn btn-default comment-submit"
							onclick="replyCommentp(this)">发表</button>
					</p>

				</div>

			</div>

			<div class="col-md-3">
				<div class="panel panel-default">

					<ul class="list-group">
						<li class="list-group-item">标题：${doc.docName }</li>
						<li class="list-group-item">标题：${doc.docType.parentType.typeName }
							${doc.docType.typeName }</li>
						<li class="list-group-item">简介：${doc.docDesc }</li>
						<li class="list-group-item">作者：${doc.docOwnUser.userName }</li>
						<li class="list-group-item">上传时间：${doc.docDate }</li>
						<li class="list-group-item">大小：${doc.docSize }KB</li>

						<li class="list-group-item"><a
							href="doc/down-load-doc?docId=${doc.docId }"
							class="btn btn-default btn-sm">下载</a>
							<button href="#" class="btn btn-default btn-sm">收藏</button>
							<button href="#" class="btn btn-default btn-sm">评论</button></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<input id="docId" value="${doc.docId }" type="hidden">
	<%@ include file="/WEB-INF/content/public/footer.jspf"%>

</body>
<script type="text/javascript">
	var docId = $("#docId").val();
	//实例化编辑器
	var um = UM.getEditor('myEditor');
	um.setContent('<br>');
	um.ready(function() {
		toolbars: [ [ 'anchor', //锚点
		'undo', //撤销
		'redo', //重做
		'bold', //加粗

		] ]
	});

	function replyCommentp(mythis) {
		var pId = null;
		$.post(
						"json/json-doc-add-comments",
						{
							"docId" : docId,
							"commentContext" : um.getContent(),
							"parentId" : pId
						},
						function(data) {
							var insertSr = "<li class='list-group-item'>"
									+ "<div class='panel panel-default'>"
									+ "<ul class='list-group'>"
									+ "<li class='list-group-item'><img src='upload/photo/"+data.comment.commentUser.userId+".png' class='small-photo'> <b>"
									+ data.comment.commentUser.userName
									+ "：</b> "
									+ data.comment.commentTime
									//+ " <a href='#'>删除</a>"
									+ data.comment.commentContext
									+ "</li>"
									+ "<li class='list-group-item commonent-child'><button class='reply-btn' onclick='replyComment(this,"
									+ data.comment.commentId
									+ ")'>回复</button></li>" + "</ul>"
									+ "</div>" + "</li>";

							//	$(".comment-box").eq(0).find("li").eq(0).append( insertSr);
							$(insertSr).insertAfter($(".comment-box").eq(0).find("li").eq(0));
							um.setContent('<br>');
						})
	}

	var editor_str = "<form class='reply'><textarea class=" + "'form-control'" + "rows=" + "'3'" + "placeholder=" + "评论.." + "></textarea><br></form>";

	$('#documentViewer').FlexPaperViewer({
		config : {

			SWFFile : escape('doc/down-load-doc?ext=swf&DocId=' + docId),
			Scale : 0.6,
			ZoomTransition : 'easeOut',
			ZoomTime : 0.5,
			ZoomInterval : 0.2,
			FitPageOnLoad : false,
			FitWidthOnLoad : true,
			FullScreenAsMaxWindow : false,
			ProgressiveLoading : false,
			MinZoomSize : 0.2,
			MaxZoomSize : 5,
			SearchMatchAll : false,
			InitViewMode : 'Portrait',
			RenderingOrder : 'flash',
			StartAtPage : '',

			ViewModeToolsVisible : true,
			ZoomToolsVisible : true,
			NavToolsVisible : true,
			CursorToolsVisible : true,
			SearchToolsVisible : true,
			WMode : 'window',
			localeChain : 'zh_CN'
		}
	});

	var pageNum = 0;
	function getMoreInfo() {
		var uri = "json/json-doc-comments?docId=" + docId + "&page=" + pageNum;
		$
				.post(
						uri,
						function(data) {
							//alert(data)
							if (data.cPage == null || data.cPage == '') {
								$("#getMore").remove();
							}
							for (var i = 0; i < data.cPage.length; i++) {

								var cstr = "";
								for (j = 0; j < data.cPage[i].childrenComment.length; j++) {
									cstr += "<li class='list-group-item commonent-child'><img src='upload/photo/"+data.cPage[i].childrenComment[j].commentUser.userId+".png' class='small-photo'><b> "
											+ data.cPage[i].childrenComment[j].commentUser.userName
											+ "：</b>"
											+ data.cPage[i].childrenComment[j].commentTime
											//+ " <a href='"+data.cPage[i].childrenComment[j].commentId+"'>删除</a>"
											+ "<p>"
											+ data.cPage[i].childrenComment[j].commentContext
											+ "</p>" + "</li>";

								}

								var insertSr = "<li class='list-group-item'>"
										+ "<div class='panel panel-default'>"
										+ "<ul class='list-group'>"
										+ "<li class='list-group-item'><img src='upload/photo/"+data.cPage[i].commentUser.userId+".png' class='small-photo'> <b>"
										+ data.cPage[i].commentUser.userName
										+ "：</b>"
										+ data.cPage[i].commentTime
										//+ " <a href='"+data.cPage[i].commentId+"'>删除</a>"
										+ "<p>"
										+ data.cPage[i].commentContext
										+ "</p>"
										+ "</li>"
										+ cstr
										+ "<li class='list-group-item commonent-child'><button class='reply-btn' onclick='replyComment(this"
										+ "," + data.cPage[i].commentId
										+ ")'>回复</button></li>" + "</ul>"
										+ "</div>" + "</li>";

								$(insertSr).insertBefore($("#getMore"));

							}
							if (data.cPage.length < 10) {
								$("#getMore").remove();
							}

						})
		pageNum++;
	}

	function replyComment(mythis, pId) {

		var $p = $(mythis).parent();
		if ($p.has("form").length > 0) {
			//回复信息
			$
					.post(
							"json/json-doc-add-comments",
							{
								"docId" : docId,
								"commentContext" : $p.find("textarea").val(),
								"parentId" : pId
							},
							function(data) {
								var replyStr = "<li class='list-group-item commonent-child'><img src='upload/photo/"+data.comment.commentUser.userId+".png' class='small-photo'> <b>"
										+ data.comment.commentUser.userName
										+ ":</b>  "
										+ data.comment.commentTime
										+ " <a href='#'>删除</a><p>"
										+ data.comment.commentContext
										+ "<p></li>";
								//alert($p.find("textarea").val());
								$(replyStr).insertBefore(
										$p.parent().find("li").last());
								$p.find("textarea").val("")
							})

		} else {
			$(".comment-box").find(".reply").remove();
			$p.prepend(editor_str);
		}
	}

	getMoreInfo()
</script>
</html>
