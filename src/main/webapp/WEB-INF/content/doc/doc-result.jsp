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


						<li class="list-group-item comment-text">
							<!--style给定宽度可以影响编辑器的最终宽度--> <script type="text/plain"
								id="myEditor" style="width: 100%; height: 200px;">
									<p>评论这篇文档..</p>
								</script>

						</li>
					</ul>
					<p>
						<button class="btn btn-default comment-submit"
							onclick="getContent()">发表</button>
					</p>

				</div>

			</div>

			<div class="col-md-3">
				<div class="panel panel-default">

					<ul class="list-group">
						<li class="list-group-item">标题：${doc.docName }</li>
						<li class="list-group-item">简介：${doc.docDesc }</li>
						<li class="list-group-item">作者：${doc.docOwnUser.userName }</li>
						<li class="list-group-item">上传时间：${doc.docDate }</li>
						<li class="list-group-item">大小：${doc.docSize }KB</li>

						<li class="list-group-item">
							<a href="doc/down-load-doc?docId=${doc.docId }"
								class="btn btn-default btn-sm">下载</a>
							<button href="#" class="btn btn-default btn-sm">收藏</button>
							<button href="#" class="btn btn-default btn-sm">评论</button>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<input id="docId" value="${doc.docId }" type="hidden">
	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script type="text/javascript">
	//实例化编辑器
	var um = UM.getEditor('myEditor');

	function getContent() {
		var arr = [];
		arr.push(um.getContent());
		var insertSr = "<li class='list-group-item'>"
				+ "<div class='panel panel-default'>"
				+ "<ul class='list-group'>"
				+ "<li class='list-group-item'><img src='img/pass.png' class='small-photo'> <b>yyf：</b> 2015-12-23 21:23:19 <a href='#'>删除</a>"
				+ arr
				+ "</li>"
				+ "<li class='list-group-item commonent-child'><button class='reply-btn' onclick='replyComment(this)'>回复</button></li>"
				+ "</ul>" + "</div>" + "</li>";
		$(insertSr).insertBefore($(".comment-box").eq(0).find("li").last());
	}

	var editor_str = "<form class='reply'><textarea class=" + "'form-control'" + "rows=" + "'3'" + "placeholder=" + "评论.." + "></textarea><br></form>";

	function replyComment(mythis) {
		var $p = $(mythis).parent();
		if ($p.has("form").length > 0) {
			//回复信息
			var replyStr = "<li class='list-group-item commonent-child'><img src='img/pass.png' class='small-photo'> <b>ysh:</b>  2015-12-23 21:23:19 <a href='#'>删除</a><p>"
					+ $p.find("textarea").val() + "<p></li>";
			//alert($p.find("textarea").val());
			$(replyStr).insertBefore($p.parent().find("li").last());
			$p.find("textarea").val("")
		} else {
			$(".comment-box").find(".reply").remove();
			$p.prepend(editor_str);
		}
	}
	var insertSr = "<li class='list-group-item'>"
			+ "<div class='panel panel-default'>"
			+ "<ul class='list-group'>"
			+ "<li class='list-group-item'><img src='img/pass.png' class='small-photo'> <b>yyf：</b> 2015-12-23 21:23:19 <a href='#'>删除</a>"
			+ "<p>这篇文档还不错，赞一个</p>"
			+ "</li>"
			+ "<li class='list-group-item commonent-child'><button class='reply-btn' onclick='replyComment(this)'>回复</button></li>"
			+ "</ul>" + "</div>" + "</li>";

	for (var i = 0; i < 5; i++) {
		$(insertSr).insertBefore($(".comment-box").eq(0).find("li").last());
	}
	var docId = $("#docId").val();

	$('#documentViewer')
			.FlexPaperViewer(
					{
						config : {

							SWFFile : escape('doc/down-load-doc?ext=swf&DocId='
									+ docId),
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
</script>
</html>
