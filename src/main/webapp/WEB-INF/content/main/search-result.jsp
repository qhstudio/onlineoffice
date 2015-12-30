<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/content/public/commons.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/content/public/head.jspf"%>
	<div class="container content-box">
		<div class="panel">
			<div class="panel-heading">
				<ol class="breadcrumb">
					<li><a href="main/index">首页</a></li>
					<li class="active">搜索结果</li>
				</ol>
			</div>
			<div class="panel-body">
				<ul class="list-group doc-list" id="doc-list-ul">
					<s:iterator value="resultList" status='st'>


						<li class="list-group-item"><a
							href="main/doc-result.action?docId=${docId }"><img
								src="img/icon-${docFoot}.png"> ${docName }</a>
							<p>上传者:${docOwnUser.userName }&nbsp;&nbsp;&nbsp;上传时间：<s:date name="%{resultList[#st.index].docDate}" format="yyyy年MM月dd日 HH:mm:ss"/></p>
							<p>${docDesc}</p></li>
					</s:iterator>
				</ul>
				<p id="get-more-btn-p">
					<!-- <button onclick="getMoreResult()" id="get-more-btn">查看更多</button> -->
				</p>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<!-- <script>
	var nowPage = 0;
	function getMoreResult() {
		$.post("main/recentListInfo",{
							pageNum : nowPage,
						},function(data) {
							if(data.docs.length < 10){
								$("#get-more-btn").parent().append("没有更多");
								$("#get-more-btn").remove();
							}
							for (var i = 0; i < data.docs.length; i++) {
								var str = "<li class=" + "'list-group-item'" + "><a href=" + "'main/doc-result.action?docId="+data.docs[i].docId+"'" 
										+"><img src=" + "'img/icon-"+ data.docs[i].docFoot+".png'" +"> "
										+ data.docs[i].docName
										+ "</a><p>上传者:"
										+ data.docs[i].docOwnUser.userName
										+ "&nbsp;&nbsp;&nbsp;上传时间："
										+ data.docs[i].docDate
										+ " </p><p>"
										+ data.docs[i].docDesc + "</p></li>";
								$("#doc-list-ul").append(str);
							}
						});
		nowPage++;
	}
	getMoreResult();
</script>
 -->
</html>