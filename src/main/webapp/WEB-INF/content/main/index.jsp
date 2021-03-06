<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/content/public/commons.jspf"%>
<style type="text/css">
.left-inner-panel-content p {
	padding: 0;
	margin: 0;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/content/public/head.jspf"%>

	<div class="container content-box">
		<div class="row">
			<!--描述：分类-->
			<div class="col-md-3">
				<div class="left-nav" id="left-nav"></div>
			</div>
			<div class="col-md-9 right-nav">
				<!--描述：焦点图-->
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<a href="#"><img src="img/c1.jpg" alt=""></a>

							<div class="carousel-caption">
								<h3>气质美美</h3>
								<p>气质美美过圣诞~</p>
							</div>

						</div>
						<div class="item">
							<a href="#"><img src="img/c2.jpg" alt=""></a>
							<div class="carousel-caption">
								<h3>气质美美</h3>
								<p>气质美美过圣诞~</p>
							</div>
						</div>
						<div class="item">
							<a href="#"><img src="img/c3.jpg" alt=""></a>
							<div class="carousel-caption">
								<h3>气质美美</h3>
								<p>气质美美过圣诞~</p>
							</div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>

			</div>
		</div>
		<hr />
		<!--描述：首页最近热门-->
		<div class="row index-middle">
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<a href="#">最新上传</a><a href="main/recent" class="rigtht-more">查看更多</a>
					</div>
					<div class="panel-body">
						<ul id="recent-index">
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<a href="#">热门文档</a><a href="main/recent" class="rigtht-more">查看更多</a>
					</div>
					<div class="panel-body">
						<ul id="hot-index">
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<a href="#">个人信息</a>
					</div>
					<div class="panel-body">
						<div class="row" id="personal-center">
							<div class="col-md-5">
								<s:if test="#session.user != null">
									<p align="center">${user.userName}</p>
									<img src="upload/photo/${user.userId}.png"
										class="img-thumbnail">
								</s:if>
							</div>
							<div class="col-md-5">
								<s:if test="#session.user == null">
									<h3>请先登录！</h3>
								</s:if>
								<s:else>
									<a class="btn btn-block btn-warning" href="doc/upload">上传文档</a>
									<a class="btn btn-block btn-warning" href="doc/mydoc-list">我的文库</a>
									<a class="btn btn-block btn-warning">消息 <span class="badge">3</span></a>
								</s:else>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script type="text/javascript">
function getMoreResult() {
		$.post("main/recentListInfo",{
							pageNum : 0,
						},
						function(data) {
							var len = data.docs.length;
							for (var i = 0; i < (len > 5?5:len); i++) {
								var str = "<li><a href="+"'main/doc-result.action?docId="+data.docs[i].docId+"'"+"><img src=" + "'img/icon-"+ data.docs[i].docFoot+".png'" +"> "+ data.docs[i].docName+"</a></li>"
								$("#recent-index").append(str);
								$("#hot-index").append(str);
							}
						});
	}
getMoreResult()

	var type_data;
	$.post("json/json-doc-types", function(data) {
		var len = (data.doctypes.length>4)?4:data.doctypes.length;
		for (var i = 0; i < len; i++) {
			var cstr = "<p>";
			var ilen = (data.doctypes[i].childrenDocType.length>6)?6:data.doctypes[i].childrenDocType.length;
			for (var j = 0; j < ilen; j++) {
				if(j==ilen/2){
					cstr +="</p><p>"
				}
				
				cstr += "<a href='main/all-type?searchId="+data.doctypes[i].childrenDocType[j].typeId+"'>"+data.doctypes[i].childrenDocType[j].typeName+"</a> ";
			}
			cstr +="</p>";
			var str = 
				"<div class='left-inner-panel-head'><a href='main/all-type?searchId="+data.doctypes[i].typeId+"'>"+data.doctypes[i].typeName+"</a>></div><div class='left-inner-panel-content'>"
				+cstr
				+"</div>";
				$("#left-nav").append(str);
		}
	});
	
	


</script>
</html>